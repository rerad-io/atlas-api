package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import com.example.medatlas.util.ModelCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for AnatomicalStructureSubjectMapper")
public class AnatomicalStructureSubjectMapperTest {
    private final AnatomicalStructureSubjectMapper anatomicalStructureSubjectMapper = Mappers.getMapper(AnatomicalStructureSubjectMapper.class);

    @Test
    @DisplayName("When we have correct entity then return correct AnatomicalStructureSubjectDTO")
    void testAnatomicalStructureSubjectToDTO() {
        AnatomicalStructureSubject subject = ModelCreator.getAnatomicalStructureSubject();
        AnatomicalStructureSubjectDTO subjectDTO = anatomicalStructureSubjectMapper.toDTO(subject);
        assertAll(
                () -> assertEquals(subject.getId(), subjectDTO.getId()),
                () -> assertEquals(subject.getName(), subjectDTO.getName()),
                () -> assertEquals(subject.getColor(), subjectDTO.getColor())
        );
    }
}