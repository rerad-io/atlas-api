package com.example.medatlas.dto;

import com.example.medatlas.model.enums.InstanceDataType;

public class InstanceDataDTO {
    private String name;
    private StudyDTO studyDTO;
    private SeriesDTO seriesDTO;
    private AnatomicalStructureDTO anatomicalStructureDTO;
    private int instanceNumber;
    private InstanceDataType type;
    private int x;
    private int y;
    private String Path;

}