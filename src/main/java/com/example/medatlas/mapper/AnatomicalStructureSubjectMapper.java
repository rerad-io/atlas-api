package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {
//    AnatomicalStructureSubjectMapper INSTANCE = Mappers.getMapper(AnatomicalStructureSubjectMapper.class);

    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);
}