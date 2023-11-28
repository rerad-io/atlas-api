package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureService {
    List<AnatomicalStructureDTO> getAllAnatomicalStructures();

    AnatomicalStructureDTO getAnatomicalStructureById(UUID id);

    List<AnatomicalStructureDTO> searchAnatomicalStructures(String name, UUID anatomicalStructureSubjectId, String orderBy, String orderByDirection);

    AnatomicalStructureDTO updateAnatomicalStructure(UUID id, AnatomicalStructureDTO structureDTO);

    void deleteAnatomicalStructure(UUID id);

    AnatomicalStructureDTO createAnatomicalStructureWithSubject(AnatomicalStructureDTO anatomicalStructureDTO, AnatomicalStructureSubjectWithoutStructuresDTO anatomicalStructureSubject);

    AnatomicalStructureSubjectWithoutStructuresDTO getAnatomicalStructureSubjectByStructureId(UUID id);
}