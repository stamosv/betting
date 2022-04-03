package com.example.bettingproject.core.port.outbound.repository;

import com.example.bettingproject.adapter.outbound.persistence.entity.MatchOddT;
import com.example.bettingproject.shared.model.MatchOddD;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface OddRepository {

    Optional<List<MatchOddD>> saveOdds(List<MatchOddT> oddTs);
}
