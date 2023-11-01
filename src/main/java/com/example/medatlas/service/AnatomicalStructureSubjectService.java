package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureSubjectService {
    AnatomicalStructureSubjectWithoutStructuresDTO createAnatomicalStructureSubject(AnatomicalStructureSubjectDTO subjectDTO);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectById(UUID id);

    List<AnatomicalStructureDTO> getChildrenBySubjectId(UUID subjectId);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectWithChildren(UUID id);

    List<AnatomicalStructureSubjectWithoutStructuresDTO> getAllAnatomicalStructureSubjects();

    AnatomicalStructureSubjectDTO updateAnatomicalStructureSubject(UUID id, AnatomicalStructureSubjectDTO subject);

    void deleteAnatomicalStructureSubject(UUID id);

    boolean existsSubjectById(UUID id);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectByStructureId(UUID id);
}