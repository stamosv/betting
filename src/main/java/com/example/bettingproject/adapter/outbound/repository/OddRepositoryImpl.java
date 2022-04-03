package com.example.bettingproject.adapter.outbound.repository;

import static com.example.bettingproject.shared.constants.BettingConstants.ERROR_AT_SAVE_ODDS_FOR_ENTITY;

import com.example.bettingproject.adapter.outbound.mappers.MatchOddMapper;
import com.example.bettingproject.adapter.outbound.persistence.entity.MatchOddT;
import com.example.bettingproject.adapter.outbound.persistence.repository.OddEntityRepository;
import com.example.bettingproject.core.port.outbound.repository.OddRepository;
import com.example.bettingproject.shared.model.MatchOddD;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OddRepositoryImpl implements OddRepository {

    private OddEntityRepository entityRepository;

    @Autowired
    public OddRepositoryImpl(OddEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Optional<List<MatchOddD>> saveOdds(List<MatchOddT> oddTs) {
        try {
            oddTs = entityRepository.saveAll(oddTs);
            return Optional.of(MatchOddMapper.INSTANCE.toListDomain(oddTs));
        } catch(Exception e) {
            log.error(ERROR_AT_SAVE_ODDS_FOR_ENTITY, oddTs);
            return Optional.empty();
        }
    }

    public Optional<MatchOddD> saveOrUpdateOdd(MatchOddT oddT) {
        oddT = entityRepository.save(oddT);
        return Optional.of(MatchOddMapper.INSTANCE.toDomain(oddT));
    }

    public Optional<MatchOddD> findByMatchIdAndSpecifier(Long matchId, String specifier) {
        Optional<MatchOddT> matchOddT = entityRepository.findByMatchIdAndSpecifier(matchId, specifier);
        return matchOddT.map(MatchOddMapper.INSTANCE::toDomain);
    }

}
