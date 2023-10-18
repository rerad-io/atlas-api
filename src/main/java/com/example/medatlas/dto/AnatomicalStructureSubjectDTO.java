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
public class AnatomicalStructureSubjectDTO {
    private UUID id;
    private String name;
    private String color;
}