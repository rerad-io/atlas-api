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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
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
    void anatomicalStructureDtoList() throws Exception {
        final List<AnatomicalStructureDTO> anatomicalStructureDTOList = DTOCreator.getAnatomicalStructureDtoList();
        when(anatomicalStructureService.getAllAnatomicalStructures()).thenReturn(anatomicalStructureDTOList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/anatomical-structures"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(anatomicalStructureDTOList.get(0).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subject", is(anatomicalStructureDTOList.get(0).getSubject())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(anatomicalStructureDTOList.get(0).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(anatomicalStructureDTOList.get(1).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].subject", is(anatomicalStructureDTOList.get(1).getSubject())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is(anatomicalStructureDTOList.get(1).getName())));
    }
}