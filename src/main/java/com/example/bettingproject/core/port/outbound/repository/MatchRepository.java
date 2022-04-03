package com.example.bettingproject.core.port.outbound.repository;

import com.example.bettingproject.adapter.outbound.persistence.entity.MatchT;
import com.example.bettingproject.shared.model.MatchD;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository {

    Optional<MatchD> findByDescriptionAndSport(String description);

    Optional<MatchD> saveOrUpdateMatch(MatchT matchT);

    boolean removeMatch(MatchT matchT);

    Optional<MatchD> findById(Long matchId);

}
