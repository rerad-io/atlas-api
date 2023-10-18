package com.example.medatlas.dto;

import com.example.medatlas.model.enums.InstanceDataType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstanceDataDTO {
    private UUID id;
    private String name;
    private StudyDTO studyDTO;
    private SeriesDTO seriesDTO;
    private AnatomicalStructureDTO anatomicalStructureDTO;
    private int instanceNumber;
    private InstanceDataType type;
    private int x;
    private int y;
    @Column(columnDefinition = "jsonb")
    private String path;
}