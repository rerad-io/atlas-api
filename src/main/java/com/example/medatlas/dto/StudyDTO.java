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
@ApiModel(description = "Study")
public class StudyDTO {
    private UUID id;
    private String externalId;
    private String name;
    private String description;
    private String previewFrame;
    private List<SeriesDTOWithoutStudy> seriesList;
    private List<InstanceDataDTO> instanceDataList;
}