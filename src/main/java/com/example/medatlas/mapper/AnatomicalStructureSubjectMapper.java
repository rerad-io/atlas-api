package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class AnatomicalStructureSubjectMapper {

    public AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject) {
        AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setColor(subject.getColor());
        return subjectDTO;
    }

    public AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubject subject = new AnatomicalStructureSubject();
        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        subject.setColor(subjectDTO.getColor());

        return subject;
    }

    public List<AnatomicalStructureSubjectDTO> toDTOList(List<AnatomicalStructureSubject> subjects) {
        return subjects.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}