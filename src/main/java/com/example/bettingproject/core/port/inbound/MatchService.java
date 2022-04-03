package com.example.bettingproject.core.port.inbound;

import com.example.bettingproject.shared.model.MatchD;
import com.example.bettingproject.shared.model.MatchOddD;
import com.example.bettingproject.shared.model.ResponseDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MatchService {

    ResponseEntity<MatchD> retrieveFootballMatch(String description);

    ResponseEntity<ResponseDTO> addMatch(MatchD match);

    ResponseEntity<ResponseDTO> addOddsToMatch(Long matchId, List<MatchOddD> oddDs);

    ResponseEntity<ResponseDTO> findAndUpdateOdd(Long matchId, String specifier, double odd);

    ResponseEntity<ResponseDTO> findSportById(Long matchId);

    ResponseEntity<ResponseDTO> removeMatch(Long matchId);
}
