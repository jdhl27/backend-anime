package com.apime.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    private Long malId;
    private String title;
    private double score;
    private String recommendation;
    private String imageUrl;
    private String trailerUrl;
    private int year;
}
