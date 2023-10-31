package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.service.AnatomicalStructureService;
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

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(AnatomicalStructureController.class)
@DisplayName("Test class for AnatomicalStructureController")
public class AnatomicalStructureControllerTest {
    @MockBean
    private AnatomicalStructureService anatomicalStructureService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAnatomicalStructureById() throws Exception {
        AnatomicalStructureDTO structure = DTOCreator.createAnatomicalStructureDTO();
        UUID id = structure.getId();
        when(anatomicalStructureService.getAnatomicalStructureById(id)).thenReturn(structure);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/AnatomicalStructure/" + id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(structure.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(structure.getName())));
    }

    @Test
    void updateAnatomicalStructure() throws Exception {
        AnatomicalStructureDTO updatedStructure = DTOCreator.createAnatomicalStructureDTO();
        UUID id = updatedStructure.getId();
        when(anatomicalStructureService.updateAnatomicalStructure(id, updatedStructure)).thenReturn(updatedStructure);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/AnatomicalStructure/" + id)
                        .contentType("application/json")
                        .content(DTOCreator.asJsonString(updatedStructure)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedStructure.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(updatedStructure.getName())));
    }

    @Test
    void deleteAnatomicalStructure() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/AnatomicalStructure/" + id))
                .andExpect(status().isOk());
    }
}
