package com.example.bettingproject.shared.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchOddD implements Serializable {

    private Long id;
    private String specifier;
    private double odd;
    private MatchD match;

}
