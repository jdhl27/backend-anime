package com.apime.service;

import org.springframework.web.client.RestTemplate;
@Service
public class AnimeService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://api.jikan.moe/v4";
}
