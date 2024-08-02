package com.apime.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JikanAnime {
    private Long mal_id;
    private String title;
    private double score;
    private Images images;
    private Trailer trailer;
    private int year;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Images {
        private ImageType jpg;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ImageType {
            private String image_url;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Trailer {
        private String url;
    }
}
