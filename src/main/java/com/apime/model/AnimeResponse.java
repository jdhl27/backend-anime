package com.apime.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeResponse {
    private Integer total_pages;
    private double avarage_score;
    private List<Anime> data;
}
