package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {
    //   AnatomicalStructureSubjectMapper INSTANCE = Mappers.getMapper(AnatomicalStructureSubjectMapper.class);
    @Mapping(source = "id", target = "id")
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);

    @Mapping(source = "id", target = "id")
    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);
}