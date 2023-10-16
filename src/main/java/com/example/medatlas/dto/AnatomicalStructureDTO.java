package com.example.medatlas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnatomicalStructureDTO {

    private UUID id;
    private AnatomicalStructureSubjectDTO subjectDTO;
    private String name;

    public AnatomicalStructureSubjectDTO getSubject() {
        return subjectDTO;
    }
}