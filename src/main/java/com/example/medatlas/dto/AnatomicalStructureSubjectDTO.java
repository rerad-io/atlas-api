package com.example.medatlas.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Anatomical Structure Subject")
public class AnatomicalStructureSubjectDTO {

    private UUID id;
    private String name;
    private String color;
    private List<AnatomicalStructureWithoutSubjectDTO> anatomicalStructures;
}