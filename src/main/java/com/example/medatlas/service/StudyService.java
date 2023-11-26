package com.example.medatlas.service;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;

import java.util.List;
import java.util.UUID;

public interface StudyService {
    StudyDTO createStudy(StudyDTO studyDTO);

    StudyDTO getStudyById(UUID id);

    List<StudyDTO> getAllStudies();

    StudyDTO updateStudy(UUID id, StudyDTO studyDTO);

    void deleteStudy(UUID id);

    List<SeriesDTOWithoutStudy> getSeriesForStudy(UUID id);

    List<InstanceDataDTO> getInstanceDataForStudy(UUID id);
}