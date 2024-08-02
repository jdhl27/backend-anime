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
    private String type;
    private String synopsis;
    private Double score;
    private String recommendation;
    private String color;
    private String imageUrl;
    private String trailerUrl;
    private int year;
}
