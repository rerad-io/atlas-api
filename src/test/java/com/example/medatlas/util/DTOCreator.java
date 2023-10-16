package com.example.medatlas.util;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTOCreator {

    public static AnatomicalStructureDTO createAnatomicalStructureDTO() {
        AnatomicalStructureDTO structureDTO = new AnatomicalStructureDTO();
        structureDTO.setId(UUID.randomUUID());
        structureDTO.setSubjectDTO(createAnatomicalStructureSubjectDTO());
        structureDTO.setName("Sample Name");
        return structureDTO;
    }

    public static List<AnatomicalStructureDTO> createAnatomicalStructureDTOList(int count) {
        List<AnatomicalStructureDTO> anatomicalStructureDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            anatomicalStructureDTOList.add(createAnatomicalStructureDTO());
        }
        return anatomicalStructureDTOList;
    }

    public static AnatomicalStructureSubjectDTO createAnatomicalStructureSubjectDTO() {
        AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
        subjectDTO.setId(UUID.randomUUID());
        subjectDTO.setName("Sample Name");
        subjectDTO.setColor("Sample Color");
        return subjectDTO;
    }

    public static List<AnatomicalStructureDTO> getAnatomicalStructureDtoList() {
        return createAnatomicalStructureDTOList(2); // Пример списка из двух элементов
    }

    public static AnatomicalStructureSubjectDTO getSubject() {
        return createAnatomicalStructureSubjectDTO();
    }

    public static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
