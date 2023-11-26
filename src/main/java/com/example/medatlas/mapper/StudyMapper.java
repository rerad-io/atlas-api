package com.example.medatlas.mapper;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.model.InstanceData;
import com.example.medatlas.model.Study;
import com.example.medatlas.repository.StudyRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {
    @Mapping(target = "seriesList", ignore = true)
    Study toEntity(StudyDTO studyDTO);

    StudyDTO toDTO(Study study);

    StudyDTO toDTO(StudyRepository.StudySummary studySummary);
}