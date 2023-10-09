package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnatomicalStructureSubjectMapper {
    AnatomicalStructureSubjectMapper INSTANCE = Mappers.getMapper(AnatomicalStructureSubjectMapper.class);

    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);
}