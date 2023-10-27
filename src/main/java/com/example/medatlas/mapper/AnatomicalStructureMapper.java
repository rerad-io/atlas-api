package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class AnatomicalStructureMapper {
    public AnatomicalStructureDTO toDTO(AnatomicalStructure structure) {
        AnatomicalStructureDTO structureDTO = new AnatomicalStructureDTO();
        structureDTO.setId(structure.getId());
        structureDTO.setName(structure.getName());

        // Маппинг родительской сущности, если она есть
//        if (structure.getSubject() != null) {
//            structureDTO.setSubjectDTO(toAnatomicalStructureSubjectDTO(structure.getSubject()));
//        }
        return structureDTO;
    }

    public AnatomicalStructure toEntity(AnatomicalStructureDTO structureDTO) {
        AnatomicalStructure structure = new AnatomicalStructure();
        structure.setId(structureDTO.getId());
        structure.setName(structureDTO.getName());

        return structure;
    }

    public List<AnatomicalStructureDTO> toDTOList(List<AnatomicalStructure> structures) {
        return structures.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AnatomicalStructureSubjectDTO toAnatomicalStructureSubjectDTO(AnatomicalStructureSubject subject) {
        AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());

        // Маппинг детей, если они есть
        if (subject.getAnatomicalStructures() != null) {
            subjectDTO.setAnatomicalStructures(
                    subject.getAnatomicalStructures().stream()
                            .map(this::toDTO)
                            .collect(Collectors.toList())
            );
        }
        return subjectDTO;
    }
}