package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.dto.AnatomicalStructureWithoutSubjectDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureSubjectService {
    AnatomicalStructureSubjectWithoutStructuresDTO createAnatomicalStructureSubject(AnatomicalStructureSubjectDTO subjectDTO);

    List<AnatomicalStructureSubjectWithoutStructuresDTO> getAllAnatomicalStructureSubjects();

    List<AnatomicalStructureSubjectDTO> getAnatomicalStructureSubjectsByName(String name);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectById(UUID id);

    List<AnatomicalStructureWithoutSubjectDTO> getChildrenBySubjectId(UUID subjectId);

    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectWithChildren(UUID id);


    AnatomicalStructureSubjectDTO updateAnatomicalStructureSubject(UUID id, AnatomicalStructureSubjectDTO subject);

    void deleteAnatomicalStructureSubject(UUID id);

    boolean hasChildStructures(UUID id);
}