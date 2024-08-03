package com.apime.service;

import com.apime.model.AnimeResponse;
import com.apime.model.JikanResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AnimeService animeService;

    @Test
    public void testSearchAnime() {
        // Configuración de un JikanResponse con datos vacíos
        JikanResponse jikanResponse = new JikanResponse();
        jikanResponse.setData(Collections.emptyList());  // Aseguramos que la lista de datos esté vacía
        JikanResponse.Pagination pagination = new JikanResponse.Pagination();
        pagination.setLast_visible_page(1);  // Configuramos la paginación
        jikanResponse.setPagination(pagination);

        // Simulación de la respuesta de RestTemplate
        when(restTemplate.getForObject(anyString(), eq(JikanResponse.class))).thenReturn(jikanResponse);

        // Verificar que la simulación de RestTemplate esté funcionando
        JikanResponse responseMock = restTemplate.getForObject("https://api.jikan.moe/v4/anime?q=Naruto&page=1", JikanResponse.class);
        System.out.println("ResponseMock Data: " + responseMock.getData());
        System.out.println("ResponseMock Pagination: " + responseMock.getPagination().getLast_visible_page());

        // Llamada al método del servicio
        AnimeResponse response = animeService.searchAnime("Naruto", "1");

        // Verificación de la respuesta
        assertNotNull(response);  // Aseguramos que la respuesta no sea nula
        assertEquals(0, response.getData().size());  // Verificamos que la lista de datos esté vacía
        assertEquals(0.0, response.getAvarage_score(), 0.001);  // Verificamos que el promedio de la puntuación sea 0.0
        assertEquals(1, response.getTotal_pages());  // Verificamos que la paginación sea 1
        verify(restTemplate, times(1)).getForObject(anyString(), eq(JikanResponse.class));
    }
}
