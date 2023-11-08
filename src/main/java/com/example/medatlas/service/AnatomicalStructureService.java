package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureService {
    AnatomicalStructureDTO getAnatomicalStructureById(UUID id);

    List<AnatomicalStructureDTO> getAllAnatomicalStructures();

    AnatomicalStructureDTO updateAnatomicalStructure(UUID id, AnatomicalStructureDTO structureDTO);

    void deleteAnatomicalStructure(UUID id);

    AnatomicalStructureDTO createAnatomicalStructureWithSubject(AnatomicalStructureDTO anatomicalStructureDTO, AnatomicalStructureSubjectWithoutStructuresDTO anatomicalStructureSubject);

    AnatomicalStructureSubjectWithoutStructuresDTO getAnatomicalStructureSubjectByStructureId(UUID id);

    String getAnatomicalStructureNameById(String structure);
}