package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.util.ModelCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for AnatomicalStructureMapper")
public class AnatomicalStructureMapperTest {
    private final AnatomicalStructureMapper anatomicalStructureMapper = Mappers.getMapper(AnatomicalStructureMapper.class);

    @Test
    @DisplayName("When we have a correct entity, then it should return a correct DTO")
    void testEntityToDTO() {
        AnatomicalStructure anatomicalStructure = ModelCreator.createAnatomicalStructure();
        AnatomicalStructureDTO structureDTO = anatomicalStructureMapper.toDTO(anatomicalStructure);

        assertAll(
                () -> assertEquals(anatomicalStructure.getId(), structureDTO.getId()),
                () -> assertEquals(anatomicalStructure.getName(), structureDTO.getName())
        );
    }

    @Test
    void toDTO() {
    }

    @Test
    void toEntity() {
    }

    @Test
    void testToDTO() {
    }

    @Test
    void testToDTO1() {
    }

    @Test
    void testToEntity() {
    }

    @Test
    void toDTOList() {
    }

    @Test
    void toAnatomicalStructureSubjectDTO() {
    }

    @Test
    void testToDTO2() {
    }

    @Test
    void testToEntity1() {
    }
}