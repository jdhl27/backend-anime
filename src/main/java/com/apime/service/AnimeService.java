package com.apime.service;

import com.apime.model.Anime;
import com.apime.model.JikanAnime;
import com.apime.model.JikanResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://api.jikan.moe/v4";

    public List<Anime> searchAnime(String query) {
        String url = BASE_URL + "/anime?q=" + query;
        JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);
        return response.getData().stream()
                .map(this::mapToAnime)
                .collect(Collectors.toList());
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

    public double calculateAverageScore(List<Anime> animes) {
        return animes.stream().mapToDouble(Anime::getScore).average().orElse(0);
    }
}
