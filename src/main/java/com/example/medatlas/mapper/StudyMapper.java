package com.example.medatlas.mapper;

import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.model.Study;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyMapper {
    StudyMapper INSTANCE = Mappers.getMapper(StudyMapper.class);

    @Mapping(target = "seriesList", ignore = true) // Assuming you have a method to map Series
    StudyDTO toDTO(Study study);

    @Mapping(target = "seriesList", ignore = true) // Assuming you have a method to map SeriesDTO
    Study toEntity(StudyDTO studyDTO);
}