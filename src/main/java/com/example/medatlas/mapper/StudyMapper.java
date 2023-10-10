package com.example.medatlas.mapper;

import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.model.Study;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {
    StudyMapper INSTANCE = Mappers.getMapper(StudyMapper.class);

    StudyDTO toDTO(Study study);

    @Mapping(target = "seriesList", ignore = true)
        // Assuming you have a method to map SeriesDTO
    Study toEntity(StudyDTO studyDTO);

    List<StudyDTO> toDTOList(List<Study> studies);
}