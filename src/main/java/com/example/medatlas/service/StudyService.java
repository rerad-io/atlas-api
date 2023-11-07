package com.example.medatlas.service;

import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.dto.StudyWithoutSeriesDTO;

import java.util.List;
import java.util.UUID;

public interface StudyService {
    StudyWithoutSeriesDTO createStudy(StudyWithoutSeriesDTO studyDTO);

    StudyWithoutSeriesDTO getStudyById(UUID id);

    List<StudyDTO> getAllStudies();

    StudyWithoutSeriesDTO updateStudy(UUID id, StudyWithoutSeriesDTO studyDTO);

    void deleteStudy(UUID id);

    List<SeriesDTOWithoutStudy> getSeriesForStudy(UUID id);
}