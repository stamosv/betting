package com.example.bettingproject.shared.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchD implements Serializable {

    private Long id;
    private String description;
    private LocalDate matchDate;
    private String matchTime;
    private String teamA;
    private String teamB;
    private int sport;
    private List<MatchOddD> matchOdds;


}

