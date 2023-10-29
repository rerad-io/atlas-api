package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithChildrenDTO;
import com.example.medatlas.dto.AnatomicalStructureWithSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {
    AnatomicalStructureSubjectWithChildrenDTO toDTO(AnatomicalStructureSubject subject);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectWithChildrenDTO subjectDTO);

    AnatomicalStructureDTO toDTO(AnatomicalStructureSubjectWithChildrenDTO subjectDTO); // Добавьте этот метод

    AnatomicalStructureSubjectWithChildrenDTO toDTO(AnatomicalStructureDTO structureDTO);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);

    AnatomicalStructureDTO toDTO(AnatomicalStructureWithSubjectDTO structureDTO);
}