package com.example.bettingproject.restapi;

import com.example.bettingproject.shared.model.MatchD;
import com.example.bettingproject.shared.model.MatchOddD;
import com.example.bettingproject.shared.model.ResponseDTO;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("match")
public interface MatchController {

    @GetMapping(value = "/retrieveFootballMatch", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<MatchD> retrieveFootballMatch(
            @RequestParam("description") String description);

    @PostMapping(value = "/addMatch", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseDTO>  addMatch(
            @RequestBody MatchD match);

    @PostMapping(value = "/addOddsToMatch", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseDTO> addOddsToMatch(
            @RequestParam("matchId") Long matchId,
            @RequestBody List<MatchOddD> oddDs);

    @PutMapping(value = "/updateOddOfMatch", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseDTO> updateOddOfMatch(
            @RequestParam("matchId") Long matchId,
            @RequestParam("specifier") String specifier,
            @RequestParam("odd") double odd);

    @GetMapping(value = "/findSportById", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseDTO>  findSportById(
            @RequestParam("matchId") Long matchId
    );

    @DeleteMapping(value = "/removeMatch", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseDTO>  removeMatch(
            @RequestParam("matchId") Long matchId);

}
