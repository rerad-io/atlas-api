package com.example.medatlas.service.impl;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.exception.SeriesExternalIdNullException;
import com.example.medatlas.mapper.InstanceDataMapper;
import com.example.medatlas.mapper.SeriesMapper;
import com.example.medatlas.mapper.StudyMapper;
import com.example.medatlas.model.*;
import com.example.medatlas.repository.InstanceDataRepository;
import com.example.medatlas.repository.SeriesRepository;
import com.example.medatlas.repository.StudyRepository;
import com.example.medatlas.service.StudyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public StudyDTO getStudyById(UUID id) {
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Study with ID " + id + " not found"));
        return studyMapper.toDTO(study);
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
    public List<InstanceDataDTO> getInstanceDataForStudy(UUID studyId) {
        List<InstanceData> instanceDataList = instanceDataRepository.findByStudyId(studyId);

        List<AnatomicalStructureSubject> structureSubjects = instanceDataList.stream()
                .map(InstanceData::getStructure)
                .filter(Objects::nonNull)
                .map(AnatomicalStructure::getAnatomicalStructureSubject)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<UUID, String> subjectColorMap = structureSubjects.stream()
                .collect(Collectors.toMap(AnatomicalStructureSubject::getId, AnatomicalStructureSubject::getColor));

        return instanceDataList.stream()
                .map(instanceData -> {
                    InstanceDataDTO instanceDataDTO = instanceDataMapper.toDTO(instanceData);
                    AnatomicalStructure anatomicalStructure = instanceData.getStructure();

                    if (anatomicalStructure != null) {
                        AnatomicalStructureSubject anatomicalStructureSubject = anatomicalStructure.getAnatomicalStructureSubject();
                        if (anatomicalStructureSubject != null) {
                            UUID subjectId = anatomicalStructureSubject.getId();
                            String subjectColor = subjectColorMap.get(subjectId);
                            instanceDataDTO.setSubjectColor(subjectColor);
                        }
                    }
                    return instanceDataDTO;
                })
                .collect(Collectors.toList());
    }
}