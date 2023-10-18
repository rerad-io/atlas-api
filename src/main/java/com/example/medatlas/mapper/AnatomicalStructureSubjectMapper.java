package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {
    @Mapping(source = "id", target = "id")
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);

    @Mapping(source = "id", target = "id")
    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);
}