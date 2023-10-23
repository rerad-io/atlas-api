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
@ApiModel(description = "Anatomical Structure")
public class AnatomicalStructureDTO {

    private UUID id;
    @Getter
    private AnatomicalStructureSubjectDTO subject;
    private String name;

    public void setSubjectDTO(AnatomicalStructureSubjectDTO anatomicalStructureSubjectDTO) {
    }
}