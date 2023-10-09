package com.example.medatlas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnatomicalStructureDTO {
    private AnatomicalStructureSubjectDTO subjectDTO;
    private String name;
}