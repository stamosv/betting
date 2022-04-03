package com.example.bettingproject.adapter.outbound.persistence.repository;

import com.example.bettingproject.adapter.outbound.persistence.entity.MatchT;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchEntityRepository extends JpaRepository<MatchT, Long> {

    Optional<MatchT> findByMatchDate(String lrn);

    Optional<MatchT> findByDescriptionAndSport(String description, int sport);

    Optional<MatchT> findById(Long id);

    Optional<MatchT> findByIdAndMatchOddsSpecifier(Long matchId, String specifier);

}
