package com.example.bettingproject.adapter.outbound.repository;

import static com.example.bettingproject.shared.constants.BettingConstants.ERROR_AT_DELETE_MATCH_FOR_ENTITY;
import static com.example.bettingproject.shared.constants.BettingConstants.ERROR_AT_SAVE_MATCH_FOR;

import com.example.bettingproject.adapter.outbound.mappers.MatchMapper;
import com.example.bettingproject.adapter.outbound.persistence.entity.MatchT;
import com.example.bettingproject.adapter.outbound.persistence.repository.MatchEntityRepository;
import com.example.bettingproject.core.port.outbound.repository.MatchRepository;
import com.example.bettingproject.shared.enums.SportEnum;
import com.example.bettingproject.shared.exceptions.BettingException;
import com.example.bettingproject.shared.model.MatchD;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MatchRepositoryImpl implements MatchRepository {

    private MatchEntityRepository entityRepository;

    @Autowired
    public MatchRepositoryImpl(MatchEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Optional<MatchD> findByDescriptionAndSport(String description) {
        Optional<MatchT> matchT = entityRepository.findByDescriptionAndSport(description, Integer.parseInt(SportEnum.Football.getName()));
        return matchT.map(MatchMapper.INSTANCE::toDomain);
    }

    public Optional<MatchD> findById(Long matchId) {
        Optional<MatchT> matchT = entityRepository.findById(matchId);
        return matchT.map(MatchMapper.INSTANCE::toDomain);
    }

    public Optional<MatchD> saveOrUpdateMatch(MatchT matchT) {
        log.info("saveOrUpdateMatch: {}", matchT);
        try {
            MatchT savedMatchT = entityRepository.save(matchT);
            return Optional.of(MatchMapper.INSTANCE.toDomain(savedMatchT));
        } catch(UnexpectedRollbackException | DataIntegrityViolationException e) {
            log.error(ERROR_AT_SAVE_MATCH_FOR, e);
            throw new BettingException(e);
        }
    }

    public boolean removeMatch(MatchT matchT) {
        log.info("removeMatch: {}", matchT);
        try {
            entityRepository.delete(matchT);
            return true;
        } catch(Exception e) {
            log.error(ERROR_AT_DELETE_MATCH_FOR_ENTITY, matchT);
            return false;
        }
    }

    public Optional<MatchD> findByIdAndSpecifier(Long matchId, String specifier) {
        Optional<MatchT> matchT = entityRepository.findByIdAndMatchOddsSpecifier(matchId, specifier);
        return matchT.map(MatchMapper.INSTANCE::toDomain);
    }

}
