package com.example.bettingproject.adapter.outbound.persistence.repository;

import com.example.bettingproject.adapter.outbound.persistence.entity.MatchOddT;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface OddEntityRepository extends JpaRepository<MatchOddT, Long> {

    Optional<MatchOddT> findByMatchIdAndSpecifier(Long matchId, String specifier);

}
