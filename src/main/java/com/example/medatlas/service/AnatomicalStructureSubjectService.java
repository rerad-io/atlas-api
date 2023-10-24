package com.example.medatlas.service;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;

import java.util.List;
import java.util.UUID;

public interface AnatomicalStructureSubjectService {
    AnatomicalStructureSubjectDTO createAnatomicalStructureSubject(AnatomicalStructureSubjectDTO subjectDTO);
    AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectById(UUID id);
    List<AnatomicalStructureSubjectDTO> getAllAnatomicalStructureSubjects();
    AnatomicalStructureSubjectDTO updateAnatomicalStructureSubject(UUID id, AnatomicalStructureSubjectDTO subjectDTO);
    void deleteAnatomicalStructureSubject(UUID id);
}