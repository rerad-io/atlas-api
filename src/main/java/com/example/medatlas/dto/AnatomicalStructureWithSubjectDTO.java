package com.example.medatlas.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Anatomical Structure with parent-Subject")
public class AnatomicalStructureWithSubjectDTO {
    private UUID id;
    private String name;
    @Getter
    private AnatomicalStructureSubjectDTO anatomicalStructureSubject;
}