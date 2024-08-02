package com.apime.controller;

import com.apime.model.Anime;
import com.apime.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping("/search")
    public List<Anime> searchAnime(@RequestParam String query) {
        return animeService.searchAnime(query);
    }

    @GetMapping("/average-score")
    public double getAverageScore(@RequestParam List<Anime> animes) {
        return animeService.calculateAverageScore(animes);
    }
}
