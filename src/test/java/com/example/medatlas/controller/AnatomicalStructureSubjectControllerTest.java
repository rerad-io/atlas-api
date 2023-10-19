package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import com.example.medatlas.util.DTOCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnatomicalStructureSubjectController.class)
@AutoConfigureMockMvc
@DisplayName("Test class for AnatomicalStructureSubjectController")
public class AnatomicalStructureSubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnatomicalStructureSubjectService subjectService;

    @Test
    @DisplayName("Test createSubject")
    void createSubject() throws Exception {
        AnatomicalStructureSubjectDTO subjectDTO = DTOCreator.createAnatomicalStructureSubjectDTO();
        when(subjectService.createAnatomicalStructureSubject(subjectDTO)).thenReturn(subjectDTO);

        mockMvc.perform(post("/api/AnatomicalStructureSubject")
                        .content(DTOCreator.asJsonString(subjectDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(subjectDTO.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(subjectDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(subjectDTO.getColor()));
    }

    @Test
    @DisplayName("Test getSubjectAll")
    void getSubjectAll() throws Exception {
        AnatomicalStructureSubjectDTO subjectDTO1 = DTOCreator.createAnatomicalStructureSubjectDTO();
        AnatomicalStructureSubjectDTO subjectDTO2 = DTOCreator.createAnatomicalStructureSubjectDTO();
        when(subjectService.getAllAnatomicalStructureSubjects()).thenReturn(List.of(subjectDTO1, subjectDTO2));

        mockMvc.perform(get("/api/AnatomicalStructureSubject"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(subjectDTO1.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(subjectDTO1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(subjectDTO1.getColor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(subjectDTO2.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(subjectDTO2.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value(subjectDTO2.getColor()));
    }

    @Test
    @DisplayName("Test getSubjectById")
    void getSubjectById() throws Exception {
        AnatomicalStructureSubjectDTO subjectDTO = DTOCreator.createAnatomicalStructureSubjectDTO();
        when(subjectService.getAnatomicalStructureSubjectById(subjectDTO.getId())).thenReturn(subjectDTO);

        mockMvc.perform(get("/api/AnatomicalStructureSubject/{id}", subjectDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(subjectDTO.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(subjectDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(subjectDTO.getColor()));
    }

    @Test
    @DisplayName("Test updateSubject")
    void updateSubject() throws Exception {
        AnatomicalStructureSubjectDTO subjectDTO = DTOCreator.createAnatomicalStructureSubjectDTO();
        when(subjectService.updateAnatomicalStructureSubject(subjectDTO.getId(), subjectDTO)).thenReturn(subjectDTO);

        mockMvc.perform(put("/api/AnatomicalStructureSubject/{id}", subjectDTO.getId())
                        .content(DTOCreator.asJsonString(subjectDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(subjectDTO.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(subjectDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(subjectDTO.getColor()));
    }

    @Test
    @DisplayName("Test deleteSubject")
    void deleteSubject() throws Exception {
        AnatomicalStructureSubjectDTO subjectDTO = DTOCreator.createAnatomicalStructureSubjectDTO();

        mockMvc.perform(delete("/api/AnatomicalStructureSubject/{id}", subjectDTO.getId()))
                .andExpect(status().isOk());
    }
}