package com.apime.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JikanResponse {
    private List<JikanAnime> data;
}
