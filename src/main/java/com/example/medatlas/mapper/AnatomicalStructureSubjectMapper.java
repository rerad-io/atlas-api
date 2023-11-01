package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.*;

import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {

    @Mapping(target = "anatomicalStructures", ignore = true)
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject, @Context Map<UUID, AnatomicalStructureSubjectDTO> context);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);

    //    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);
    @Mapping(target = "anatomicalStructures", ignore = true)
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);

    AnatomicalStructureSubjectWithoutStructuresDTO toAnatomicalStructureSubjectWithoutStructuresDTO(AnatomicalStructureSubject subject);
}