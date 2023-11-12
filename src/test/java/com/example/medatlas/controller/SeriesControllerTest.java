package com.example.medatlas.controller;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.service.SeriesService;
import com.example.medatlas.util.DTOCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(SeriesController.class)
@DisplayName("Test class for SeriesController")
public class SeriesControllerTest {
    @MockBean
    private SeriesService seriesService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createSeries() throws Exception {
        SeriesDTO newSeries = DTOCreator.getSeriesDTO();
        when(seriesService.createSeries(newSeries)).thenReturn(newSeries);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Series/")
                        .contentType("application/json")
                        .content(DTOCreator.asJsonString(newSeries)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(newSeries.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number", is(newSeries.getNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(newSeries.getName())));
    }

    @Test
    void getSeriesById() throws Exception {
        SeriesDTO series = DTOCreator.getSeriesDTO();
        UUID id = series.getId();
        when(seriesService.getSeriesById(id)).thenReturn(series);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Series/" + id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(series.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number", is(series.getNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(series.getName())));
    }

    @Test
    void updateSeries() throws Exception {
        SeriesDTO updatedSeries = DTOCreator.getSeriesDTO();
        UUID id = updatedSeries.getId();
        when(seriesService.updateSeries(id, updatedSeries)).thenReturn(updatedSeries);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/Series/" + id)
                        .contentType("application/json")
                        .content(DTOCreator.asJsonString(updatedSeries)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedSeries.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number", is(updatedSeries.getNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(updatedSeries.getName())));
    }

    @Test
    void deleteSeries() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Series/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void seriesDtoList() throws Exception {
        final List<SeriesDTO> seriesDTOList = DTOCreator.getSeriesDTOList();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Series"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(seriesDTOList.get(0).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number", is(seriesDTOList.get(0).getNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(seriesDTOList.get(0).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(seriesDTOList.get(1).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].number", is(seriesDTOList.get(1).getNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is(seriesDTOList.get(1).getName())));
    }
}