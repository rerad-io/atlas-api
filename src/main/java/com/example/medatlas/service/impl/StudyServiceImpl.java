package com.example.medatlas.service.impl;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.exception.SeriesExternalIdNullException;
import com.example.medatlas.mapper.InstanceDataMapper;
import com.example.medatlas.mapper.SeriesMapper;
import com.example.medatlas.mapper.StudyMapper;
import com.example.medatlas.model.InstanceData;
import com.example.medatlas.model.Series;
import com.example.medatlas.model.Study;
import com.example.medatlas.repository.InstanceDataRepository;
import com.example.medatlas.repository.SeriesRepository;
import com.example.medatlas.repository.StudyRepository;
import com.example.medatlas.service.StudyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final StudyMapper studyMapper;
    private final SeriesRepository seriesRepository;

    private final SeriesMapper seriesMapper;
    private final InstanceDataMapper instanceDataMapper;
    private final InstanceDataRepository instanceDataRepository;

    @Autowired
    public StudyServiceImpl(StudyRepository studyRepository, StudyMapper studyMapper, SeriesRepository seriesRepository, SeriesMapper seriesMapper, InstanceDataMapper instanceDataMapper, InstanceDataRepository instanceDataRepository) {
        this.studyRepository = studyRepository;
        this.studyMapper = studyMapper;
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
        this.instanceDataMapper = instanceDataMapper;
        this.instanceDataRepository = instanceDataRepository;
    }

    @Override
    public StudyDTO createStudy(StudyDTO studyDTO) {
        if (studyDTO.getExternalId() == null || studyDTO.getExternalId().isEmpty()) {
            throw new SeriesExternalIdNullException("ExternalId should not be empty & must contain 36 characters");
        }
        Study study = studyMapper.toEntity(studyDTO);
        Study savedStudy = studyRepository.save(study);
        return studyMapper.toDTO(savedStudy);
    }

//    @Override
//    public StudyDTO getStudyById(UUID id) {
//        Study study = studyRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Study with ID " + id + " not found"));
//        return studyMapper.toDTO(study);
//    }
    @Override
    public StudyDTO getStudyById(UUID id) {
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Study with ID " + id + " not found"));

        List<SeriesDTOWithoutStudy> seriesDTOList = getSeriesForStudy(id);
        List<InstanceDataDTO> instanceDataList = getInstanceDataForStudy(id);

        StudyDTO studyDTO = studyMapper.toDTO(study);
        studyDTO.setSeriesList(seriesDTOList);
        studyDTO.setInstanceDataList(instanceDataList);

        return studyDTO;
    }
    @Override
    public List<StudyDTO> getAllStudies() {
        List<StudyRepository.StudySummary> studies = studyRepository.findAllProjectedBy();
        return studies.stream()
                .map(studyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudyDTO updateStudy(UUID id, StudyDTO studyDTO) {
        Study existingStudy = studyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Study with ID " + id + " not found"));

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

    @Override
    public List<SeriesDTOWithoutStudy> getSeriesForStudy(UUID studyId) {
        List<Series> seriesList = seriesRepository.findByStudyId(studyId);
        return seriesList.stream()
                .map(seriesMapper::toDTOWithoutStudy)
                .collect(Collectors.toList());
    }

    @Override
    public String getStudyNameById(String studyId) {
        Study study = studyRepository.findById(UUID.fromString(studyId)).orElse(null);
        return (study != null) ? study.getName() : null;
    }

    @Override
    public List<InstanceDataDTO> getInstanceDataForStudy(UUID studyId) {
        List<InstanceData> instanceDataList = instanceDataRepository.findByStudyId(studyId);
        return instanceDataList.stream()
                .map(instanceDataMapper::toDTO)
                .collect(Collectors.toList());
    }
}