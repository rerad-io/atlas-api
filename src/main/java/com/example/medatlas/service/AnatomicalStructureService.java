package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithChildrenDTO;
import com.example.medatlas.dto.AnatomicalStructureWithSubjectDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureService {
    AnatomicalStructureWithSubjectDTO createAnatomicalStructure(AnatomicalStructureSubjectWithChildrenDTO structureDTO);

    AnatomicalStructureWithSubjectDTO getAnatomicalStructureById(UUID id);

    List<AnatomicalStructureDTO> getAllAnatomicalStructures();

    AnatomicalStructureWithSubjectDTO updateAnatomicalStructure(UUID id, AnatomicalStructureWithSubjectDTO structureDTO);

    void deleteAnatomicalStructure(UUID id);

    AnatomicalStructureWithSubjectDTO createAnatomicalStructureWithSubject(AnatomicalStructureDTO structureDTO);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectByStructureId(UUID id);
}