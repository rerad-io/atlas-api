package com.example.medatlas.util;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;

import java.util.UUID;

public class ModelCreator {

    public static AnatomicalStructure createAnatomicalStructure() {
        AnatomicalStructure structure = new AnatomicalStructure();
        structure.setId(UUID.randomUUID());
        structure.setSubject(createAnatomicalStructureSubject());
        structure.setName("Sample Name");
        return structure;
    }

    public static AnatomicalStructureSubject createAnatomicalStructureSubject() {
        AnatomicalStructureSubject subject = new AnatomicalStructureSubject();
        subject.setId(UUID.randomUUID());
        subject.setName("Sample Subject");
        return subject;
    }
    public static AnatomicalStructureSubject getAnatomicalStructureSubject() {
        AnatomicalStructureSubject subject = new AnatomicalStructureSubject();
        subject.setId(UUID.randomUUID());
        subject.setName("Sample Name");
        subject.setColor("Sample Color");
        return subject;
    }

    public static AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectDTO() {
        AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
        subjectDTO.setId(UUID.randomUUID());
        subjectDTO.setName("Sample Name");
        subjectDTO.setColor("Sample Color");
        return subjectDTO;
    }
}