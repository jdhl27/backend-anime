package com.apime.controller;

import com.apime.model.AnimeResponse;
import com.apime.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping("/search")
    public AnimeResponse searchAnime(@RequestParam String query, @RequestParam(required = false) String page) {
        return animeService.searchAnime(query, page);
    }
}
