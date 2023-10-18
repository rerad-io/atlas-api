package com.example.medatlas.mapper;

import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.model.Study;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {
    //    StudyMapper INSTANCE = Mappers.getMapper(StudyMapper.class);
    @Mapping(source = "id", target = "id")
    StudyDTO toDTO(Study study);

    @Mapping(target = "seriesList", ignore = true)
    Study toEntity(StudyDTO studyDTO);

    List<StudyDTO> toDTOList(List<Study> studies);
}