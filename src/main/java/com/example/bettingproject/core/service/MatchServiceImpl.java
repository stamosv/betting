package com.example.bettingproject.core.service;

import static com.example.bettingproject.shared.constants.BettingConstants.ERROR_AT_ADD_MATCH_FOR_ENTITY;
import static com.example.bettingproject.shared.constants.BettingConstants.MATCH_ENTRY_COULD_NOT_BE_DELETED;
import static com.example.bettingproject.shared.constants.BettingConstants.MATCH_ENTRY_COULD_NOT_BE_FOUND;
import static com.example.bettingproject.shared.constants.BettingConstants.MATCH_ENTRY_COULD_NOT_BE_SUBMITTED;
import static com.example.bettingproject.shared.constants.BettingConstants.MATCH_ENTRY_COULD_NOT_BE_UPDATED;
import static com.example.bettingproject.shared.constants.BettingConstants.MATCH_ENTRY_HAS_DELETED_SUCCESSFULLY;
import static com.example.bettingproject.shared.constants.BettingConstants.MATCH_ENTRY_HAS_UPDATED_SUCCESSFULLY;
import static com.example.bettingproject.shared.constants.BettingConstants.ODDS_ALREADY_EXIST;
import static com.example.bettingproject.shared.constants.BettingConstants.ODD_ENTRY_COULD_NOT_BE_SUBMITTED;
import static com.example.bettingproject.shared.constants.BettingConstants.ODD_ENTRY_HAS_UPDATED_SUCCESSFULLY;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.example.bettingproject.adapter.outbound.mappers.MatchMapper;
import com.example.bettingproject.adapter.outbound.mappers.MatchOddMapper;
import com.example.bettingproject.adapter.outbound.persistence.entity.MatchOddT;
import com.example.bettingproject.adapter.outbound.persistence.entity.MatchT;
import com.example.bettingproject.core.port.inbound.MatchService;
import com.example.bettingproject.core.port.outbound.repository.MatchRepository;
import com.example.bettingproject.core.port.outbound.repository.OddRepository;
import com.example.bettingproject.shared.enums.SportEnum;
import com.example.bettingproject.shared.exceptions.BettingException;
import com.example.bettingproject.shared.model.MatchD;
import com.example.bettingproject.shared.model.MatchOddD;
import com.example.bettingproject.shared.model.ResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final OddRepository oddRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, OddRepository oddRepository) {
        this.matchRepository = matchRepository;
        this.oddRepository = oddRepository;
    }

    public ResponseEntity<MatchD> retrieveFootballMatch(String description) {
        Optional<MatchD> opMatchD = matchRepository.findByDescriptionAndSport(description);
        return opMatchD.map(matchD -> new ResponseEntity<>(matchD, HttpStatus.OK)).orElse(null);
    }

    public ResponseEntity<ResponseDTO> addMatch(MatchD match) {
        try {
            Optional<MatchD> opMatchD = matchRepository.saveOrUpdateMatch(MatchMapper.INSTANCE.toEntity(match));
            return (opMatchD.isPresent() ?
                    new ResponseEntity<>((ResponseDTO.builder().message(MATCH_ENTRY_HAS_UPDATED_SUCCESSFULLY).build()), HttpStatus.OK) :
                    new ResponseEntity<>((ResponseDTO.builder().message(MATCH_ENTRY_COULD_NOT_BE_SUBMITTED).build()), HttpStatus.BAD_REQUEST));
        } catch(BettingException e) {
            log.error(ERROR_AT_ADD_MATCH_FOR_ENTITY, match);
            return new ResponseEntity<>(ResponseDTO.builder().message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseDTO> addOddsToMatch(Long matchId, List<MatchOddD> oddDs) {
        Optional<MatchD> opMatchD = matchRepository.findById(matchId);
        return opMatchD.isPresent() ?
                addOddsIntoDatabase(oddDs, opMatchD) :
                new ResponseEntity<>((ResponseDTO.builder().message(MATCH_ENTRY_COULD_NOT_BE_FOUND).build()), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseDTO> findAndUpdateOdd(Long matchId, String specifier, double odd) {
        Optional<MatchD> opMatchD = matchRepository.findById(matchId);
        return opMatchD.isPresent() ?
                updateSpecificOddOfMatch(specifier, odd, opMatchD) :
                new ResponseEntity<>(ResponseDTO.builder().message(MATCH_ENTRY_COULD_NOT_BE_FOUND).build(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseDTO> findSportById(Long matchId) {
        Optional<MatchD> opMatchD = matchRepository.findById(matchId);
        return opMatchD.map(matchD -> new ResponseEntity<>(ResponseDTO.builder().message(SportEnum.getEnumByString(String.valueOf(matchD.getSport()))).build(),
                               HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(ResponseDTO.builder().message(MATCH_ENTRY_COULD_NOT_BE_FOUND).build(), HttpStatus.BAD_REQUEST));
    }

    public ResponseEntity<ResponseDTO>  removeMatch(Long matchId) {
        Optional<MatchD> opMatchD = matchRepository.findById(matchId);
        return opMatchD.isPresent() ?
                processMatchRemoval(opMatchD) :
                new ResponseEntity<>(ResponseDTO.builder().message(MATCH_ENTRY_COULD_NOT_BE_FOUND).build(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ResponseDTO>  processMatchRemoval(Optional<MatchD> opMatchD) {
        return matchRepository.removeMatch(MatchMapper.INSTANCE.toEntity(opMatchD.get())) ?
                new ResponseEntity<>(ResponseDTO.builder().message(MATCH_ENTRY_HAS_DELETED_SUCCESSFULLY).build(), HttpStatus.OK) :
                new ResponseEntity<>(ResponseDTO.builder().message(MATCH_ENTRY_COULD_NOT_BE_DELETED).build(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ResponseDTO> addOddsIntoDatabase(List<MatchOddD> oddDs, Optional<MatchD> opMatchD) {
        createMatchOddsListIfNotExist(opMatchD);
        if(isEmpty(opMatchD.get().getMatchOdds())) {
            oddDs.forEach(odd -> odd.setMatch(opMatchD.get()));
            Optional<List<MatchOddD>> savedOdds = oddRepository.saveOdds(MatchOddMapper.INSTANCE.toListEntity(oddDs));
            return (savedOdds.isPresent() ?
                    new ResponseEntity<>(ResponseDTO.builder().message(MATCH_ENTRY_HAS_UPDATED_SUCCESSFULLY).build(), HttpStatus.OK) :
                    new ResponseEntity<>(ResponseDTO.builder().message(MATCH_ENTRY_COULD_NOT_BE_UPDATED).build(), HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(ResponseDTO.builder().message(ODDS_ALREADY_EXIST).build(), HttpStatus.BAD_REQUEST);
    }

    private void createMatchOddsListIfNotExist(Optional<MatchD> opMatchD) {
        if(isEmpty(opMatchD.get().getMatchOdds())) {
            opMatchD.get().setMatchOdds(new ArrayList<>());
        }
    }

    private ResponseEntity<ResponseDTO> updateSpecificOddOfMatch(String specifier, double odd, Optional<MatchD> opMatchD) {
        MatchT matchT = MatchMapper.INSTANCE.toEntity(opMatchD.get());
        Optional<MatchOddT> opMatchOddT = matchT.getMatchOdds().stream().filter(matchOdd -> specifier.equals(matchOdd.getSpecifier())).findFirst();
        if(opMatchOddT.isPresent()) {
            opMatchOddT.get().setOdd(odd);
            matchT.getMatchOdds().forEach(matchOdd -> matchOdd.setMatch(matchT));
            matchRepository.saveOrUpdateMatch(matchT);
            return new ResponseEntity<>(ResponseDTO.builder().message(ODD_ENTRY_HAS_UPDATED_SUCCESSFULLY).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseDTO.builder().message(ODD_ENTRY_COULD_NOT_BE_SUBMITTED).build(), HttpStatus.BAD_REQUEST);
    }


}
