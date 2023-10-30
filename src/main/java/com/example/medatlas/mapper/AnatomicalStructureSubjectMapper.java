package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);

    List<AnatomicalStructureSubjectDTO> toDTOList(List<AnatomicalStructureSubject> subjectList);

    List<AnatomicalStructureDTO> toAnatomicalStructureDTOList(List<AnatomicalStructureDTO> structureDTOList);
}
