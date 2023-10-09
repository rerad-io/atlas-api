package com.example.medatlas.service.impl;

import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.mapper.StudyMapper;
import com.example.medatlas.model.Study;
import com.example.medatlas.repository.StudyRepository;
import com.example.medatlas.service.StudyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final StudyMapper studyMapper;

    @Autowired
    public StudyServiceImpl(StudyRepository studyRepository, StudyMapper studyMapper) {
        this.studyRepository = studyRepository;
        this.studyMapper = studyMapper;
    }

    @Override
    public StudyDTO createStudy(StudyDTO studyDTO) {
        Study study = studyMapper.toEntity(studyDTO);
        Study savedStudy = studyRepository.save(study);
        return studyMapper.toDTO(savedStudy);
    }

    @Override
    public StudyDTO getStudyById(UUID id) {
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Study with ID " + id + " not found"));
        return studyMapper.toDTO(study);
    }

    @Override
    public List<StudyDTO> getAllStudies() {
        List<Study> studies = studyRepository.findAll();
        return studyMapper.toDTOList(studies);
    }

    @Override
    public StudyDTO updateStudy(UUID id, StudyDTO studyDTO) {
        Study existingStudy = studyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Study with ID " + id + " not found"));

        // Update the fields of existingStudy using data from studyDTO
        existingStudy.setName(studyDTO.getName());
        existingStudy.setDescription(studyDTO.getDescription());
        existingStudy.setPreviewFrame(studyDTO.getPreviewFrame());

        Study updatedStudy = studyRepository.save(existingStudy);
        return studyMapper.toDTO(updatedStudy);
    }

    @Override
    public void deleteStudy(UUID id) {
        studyRepository.deleteById(id);
    }
}