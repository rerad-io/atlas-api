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
public class StudyDTO {
    private UUID id;
    private UUID externalId;
    private String name;
    private String description;
    private String previewFrame;
}