package com.example.medatlas.dto;

import com.example.medatlas.model.enums.InstanceDataType;
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
@ApiModel(description = "Instance Data")
public class InstanceDataDTO {
    private UUID id;
    private String study;
    private String series;
    private String structure;
    private int instanceNumber;
    private InstanceDataType type;
    private int x;
    private int y;
    private String path;
}