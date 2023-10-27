package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithChildrenDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureSubjectService {
    AnatomicalStructureSubjectDTO createAnatomicalStructureSubject(AnatomicalStructureSubjectDTO subjectDTO);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectById(UUID id);

    List<AnatomicalStructureDTO> getChildrenBySubjectId(UUID subjectId);

    AnatomicalStructureSubjectWithChildrenDTO getAnatomicalStructureSubjectWithChildren(UUID id);

    List<AnatomicalStructureSubjectDTO> getAllAnatomicalStructureSubjects();

    AnatomicalStructureSubjectDTO updateAnatomicalStructureSubject(UUID id, AnatomicalStructureSubjectDTO subject);

    void deleteAnatomicalStructureSubject(UUID id);

    boolean existsSubjectById(UUID id);
}