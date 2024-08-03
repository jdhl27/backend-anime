package com.apime.controller;

import com.apime.model.AnimeResponse;
import com.apime.service.AnimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimeController.class)
@AutoConfigureMockMvc
public class AnimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimeService animeService;

    @Test
    public void testSearchAnime() throws Exception {
        AnimeResponse animeResponse = new AnimeResponse();
        when(animeService.searchAnime(anyString(), anyString())).thenReturn(animeResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                .param("query", "Naruto")
                .param("page", "1"))
                .andExpect(status().isOk());

        verify(animeService, times(1)).searchAnime(anyString(), anyString());
    }
}
