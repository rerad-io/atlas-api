package com.example.medatlas.service.impl;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.mapper.InstanceDataMapper;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.InstanceData;
import com.example.medatlas.model.Series;
import com.example.medatlas.model.Study;
import com.example.medatlas.repository.AnatomicalStructureRepository;
import com.example.medatlas.repository.InstanceDataRepository;
import com.example.medatlas.repository.SeriesRepository;
import com.example.medatlas.repository.StudyRepository;
import com.example.medatlas.service.AnatomicalStructureService;
import com.example.medatlas.service.InstanceDataService;
import com.example.medatlas.service.SeriesService;
import com.example.medatlas.service.StudyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InstanceDataServiceImpl implements InstanceDataService {

    private final InstanceDataRepository instanceDataRepository;
    private final InstanceDataMapper instanceDataMapper;
    private final StudyService studyService;
    private final SeriesService seriesService;
    private final AnatomicalStructureService anatomicalStructureService;
    private final StudyRepository studyRepository;
    private final SeriesRepository seriesRepository;
    private final AnatomicalStructureRepository anatomicalStructureRepository;


    @Autowired
    public InstanceDataServiceImpl(
            InstanceDataRepository instanceDataRepository,
            InstanceDataMapper instanceDataMapper, StudyService studyService, SeriesService seriesService, AnatomicalStructureService anatomicalStructureService, StudyRepository studyRepository, SeriesRepository seriesRepository, AnatomicalStructureRepository anatomicalStructureRepository) {
        this.instanceDataRepository = instanceDataRepository;
        this.instanceDataMapper = instanceDataMapper;
        this.studyService = studyService;
        this.seriesService = seriesService;
        this.anatomicalStructureService = anatomicalStructureService;
        this.studyRepository = studyRepository;
        this.seriesRepository = seriesRepository;
        this.anatomicalStructureRepository = anatomicalStructureRepository;
    }

    @Override
    public InstanceDataDTO createInstanceData(InstanceDataDTO instanceDataDTO) {
        String studyId = instanceDataDTO.getStudy();
        String seriesId = instanceDataDTO.getSeries();
        String structureId = instanceDataDTO.getStructure();

        String studyName = studyService.getStudyNameById(studyId);
        String seriesName = seriesService.getSeriesNameById(seriesId);
        String structureName = anatomicalStructureService.getAnatomicalStructureNameById(structureId);

        Study study = studyRepository.findById(UUID.fromString(studyId)).orElse(null);
        Series series = seriesRepository.findById(UUID.fromString(seriesId)).orElse(null);
        AnatomicalStructure anatomicalStructure = anatomicalStructureRepository.findById(UUID.fromString(structureId)).orElse(null);

        InstanceData instanceData = instanceDataMapper.toEntity(instanceDataDTO);
        instanceData.setStudy(study);
        instanceData.setSeries(series);
        instanceData.setStructure(anatomicalStructure);
        instanceData.setStudyName(studyName);
        instanceData.setSeriesName(seriesName);
        instanceData.setStructureName(structureName);

        InstanceData savedInstanceData = instanceDataRepository.save(instanceData);

        InstanceDataDTO responseDTO = instanceDataMapper.toDTO(savedInstanceData);
        responseDTO.setStudy(studyName);
        responseDTO.setSeries(seriesName);
        responseDTO.setStructure(structureName);
        return responseDTO;
    }

    @Override
    public InstanceDataDTO getInstanceDataById(UUID id) {
        InstanceData instanceData = instanceDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InstanceData not found with id: " + id));
        return instanceDataMapper.toDTO(instanceData);
    }

    @Override
    public List<InstanceDataDTO> getAllInstanceData() {
        List<InstanceData> instanceDataList = instanceDataRepository.findAll();
        return instanceDataMapper.toDTOList(instanceDataList);
    }

    @Override
    public InstanceDataDTO updateInstanceData(UUID id, InstanceDataDTO instanceDataDTO) {
        InstanceData existingInstanceData = instanceDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InstanceData not found with id: " + id));

        existingInstanceData.setInstanceNumber(instanceDataDTO.getInstanceNumber());
        existingInstanceData.setType(instanceDataDTO.getType());
        existingInstanceData.setX(instanceDataDTO.getX());
        existingInstanceData.setY(instanceDataDTO.getY());
        existingInstanceData.setPath(instanceDataDTO.getPath());

        InstanceData updatedInstanceData = instanceDataRepository.save(existingInstanceData);
        return instanceDataMapper.toDTO(updatedInstanceData);
    }

    @Override
    public void deleteInstanceData(UUID id) {
        InstanceData instanceData = instanceDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InstanceData not found with id: " + id));
        instanceDataRepository.delete(instanceData);
    }
}