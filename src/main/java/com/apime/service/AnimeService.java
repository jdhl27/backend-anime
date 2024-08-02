package com.apime.service;

import com.apime.model.Anime;
import com.apime.model.AnimeResponse;
import com.apime.model.JikanAnime;
import com.apime.model.JikanResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnimeService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://api.jikan.moe/v4";

    public AnimeResponse searchAnime(String query, String page) {
        String url = BASE_URL + "/anime?q=" + query;
        if (page != null && !page.isEmpty()) {
            url += "&page=" + page;
        }
        JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);
        List<Anime> animeList = response.getData().stream()
                .map(this::mapToAnime)
                .collect(Collectors.toList());
        double average = calculateAverageScore(animeList);
        Integer totalPages = response.getPagination().getLast_visible_page();

        return new AnimeResponse(totalPages, average, animeList);
    }

    private Anime mapToAnime(JikanAnime jikanAnime) {
        Anime anime = new Anime();
        anime.setMalId(jikanAnime.getMal_id());
        anime.setTitle(jikanAnime.getTitle());
        anime.setScore(jikanAnime.getScore());
        anime.setRecommendation(getRecommendation(jikanAnime.getScore()));
        anime.setImageUrl(jikanAnime.getImages().getJpg().getImage_url());
        anime.setTrailerUrl(jikanAnime.getTrailer().getUrl());
        anime.setYear(jikanAnime.getYear());
        return anime;
    }

    private Map<String, String> getRecommendation(double score) {
        Map<String, String> recommendation = new HashMap<>();

        if (score == 0) {
            recommendation.put("message", "--");
            recommendation.put("color", "gray");
        } else if (score <= 4) {
            recommendation.put("message", "I do not recommend it.");
            recommendation.put("color", "#b92e00");
        } else if (score <= 7) {
            recommendation.put("message", "You may have fun.");
            recommendation.put("color", "#d5aa00");
        } else {
            recommendation.put("message", "Great, this is one of the best anime.");
            recommendation.put("color", "#28a745");
        }

        return recommendation;
    }

    private double calculateAverageScore(List<Anime> animes) {
        return animes.stream().mapToDouble(Anime::getScore).average().orElse(0.0);
    }
}
