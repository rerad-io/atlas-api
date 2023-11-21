package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {

    @Mapping(target = "anatomicalStructures", ignore = true)
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject, @Context Map<UUID, AnatomicalStructureSubjectDTO> context);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);

    @Mapping(target = "anatomicalStructures", ignore = true)
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);

    AnatomicalStructureSubjectWithoutStructuresDTO toAnatomicalStructureSubjectWithoutStructuresDTO(AnatomicalStructureSubject subject);

    List<AnatomicalStructureSubjectDTO> toDTOList(List<AnatomicalStructureSubject> subjectList);
}