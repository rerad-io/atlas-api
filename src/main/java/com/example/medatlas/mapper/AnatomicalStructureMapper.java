package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureWithSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithChildrenDTO;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureMapper {
    AnatomicalStructureWithSubjectDTO toDTO(AnatomicalStructure structure);

    AnatomicalStructure toEntity(AnatomicalStructureWithSubjectDTO structureDTO);

    // Методы маппинга для обработки связанных DTO
    AnatomicalStructureDTO toDTO(AnatomicalStructureWithSubjectDTO structureDTO);

    AnatomicalStructureWithSubjectDTO toDTO(AnatomicalStructureDTO structureDTO);

    AnatomicalStructure toEntity(AnatomicalStructureDTO structureDTO);

    List<AnatomicalStructureDTO> toDTOList(List<AnatomicalStructure> structureList);

    AnatomicalStructureSubjectDTO toAnatomicalStructureSubjectDTO(AnatomicalStructureSubject subject);
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubjectWithChildrenDTO subjectDTO);

    AnatomicalStructure toEntity(AnatomicalStructureSubjectWithChildrenDTO structureDTO);
}