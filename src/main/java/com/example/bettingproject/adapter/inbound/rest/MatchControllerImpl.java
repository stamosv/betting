package com.example.bettingproject.adapter.inbound.rest;

import com.example.bettingproject.core.port.inbound.MatchService;
import com.example.bettingproject.restapi.MatchController;
import com.example.bettingproject.shared.model.MatchD;
import com.example.bettingproject.shared.model.MatchOddD;
import com.example.bettingproject.shared.model.ResponseDTO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MatchControllerImpl implements MatchController {

    private final MatchService oddService;

    @Autowired
    public MatchControllerImpl(MatchService oddService) {
        this.oddService = oddService;
    }

    public ResponseEntity<MatchD> retrieveFootballMatch(String description) {
        return oddService.retrieveFootballMatch(description);
    }

    public  ResponseEntity<ResponseDTO> addMatch(MatchD matchD) {
        log.info("match: {}", matchD);
        return oddService.addMatch(matchD);
    }

    public ResponseEntity<ResponseDTO> addOddsToMatch(Long matchId, List<MatchOddD> oddDs) {
        return oddService.addOddsToMatch(matchId, oddDs);
    }

    public  ResponseEntity<ResponseDTO> updateOddOfMatch(Long matchId, String specifier, double odd) {
        return oddService.findAndUpdateOdd(matchId, specifier, odd);
    }

    public ResponseEntity<ResponseDTO>  findSportById(Long matchId) {
        return oddService.findSportById(matchId);
    }

    public ResponseEntity<ResponseDTO>  removeMatch(Long matchId) {
        return oddService.removeMatch(matchId);
    }
}
