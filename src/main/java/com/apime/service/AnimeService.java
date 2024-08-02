package com.apime.service;

import org.springframework.web.client.RestTemplate;
@Service
public class AnimeService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://api.jikan.moe/v4";

    private String getRecommendation(double score) {
        if (score == 0) {
            return "--";
        } else if (score <= 4) {
            return "I do not recommend it.";
        } else if (score <= 7) {
            return "You may have fun.";
        } else {
            return "Great, this is one of the best anime.";
        }
    }
}
