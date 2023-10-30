package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureService {
    AnatomicalStructureDTO createAnatomicalStructure(AnatomicalStructureSubjectDTO structureDTO);

    AnatomicalStructureDTO getAnatomicalStructureById(UUID id);

    List<AnatomicalStructureDTO> getAllAnatomicalStructures();

    AnatomicalStructureDTO updateAnatomicalStructure(UUID id, AnatomicalStructureDTO structureDTO);

    void deleteAnatomicalStructure(UUID id);

    AnatomicalStructureDTO createAnatomicalStructureWithSubject(AnatomicalStructureDTO structureDTO);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectByStructureId(UUID id);
}