package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureMapper {
    AnatomicalStructureDTO toDTO(AnatomicalStructure structure);

    List<AnatomicalStructureDTO> toDTOList(List<AnatomicalStructure> structureList);

    AnatomicalStructureSubjectDTO toAnatomicalStructureSubjectDTO(AnatomicalStructureSubject subject);

    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubjectDTO subjectDTO);

    AnatomicalStructure toEntity(AnatomicalStructureDTO structureDTO);

    AnatomicalStructure toEntity(AnatomicalStructureSubjectDTO structureDTO);

    List<AnatomicalStructure> toEntityList(List<AnatomicalStructureDTO> structureDTOList);
}