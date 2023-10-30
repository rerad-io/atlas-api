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
    private String name;
    @Getter
    private String subjectId;
    public void setSubjectDTO(AnatomicalStructureSubjectDTO subjectDTO) {
        this.subjectId = subjectDTO.getId().toString();
    }
}