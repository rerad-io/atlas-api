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
import com.example.medatlas.service.InstanceDataService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InstanceDataServiceImpl implements InstanceDataService {

    private final InstanceDataRepository instanceDataRepository;
    private final InstanceDataMapper instanceDataMapper;
    private final StudyRepository studyRepository;
    private final SeriesRepository seriesRepository;
    private final AnatomicalStructureRepository anatomicalStructureRepository;


    @Autowired
    public InstanceDataServiceImpl(
            InstanceDataRepository instanceDataRepository,
            InstanceDataMapper instanceDataMapper, StudyRepository studyRepository, SeriesRepository seriesRepository, AnatomicalStructureRepository anatomicalStructureRepository) {
        this.instanceDataRepository = instanceDataRepository;
        this.instanceDataMapper = instanceDataMapper;
        this.studyRepository = studyRepository;
        this.seriesRepository = seriesRepository;
        this.anatomicalStructureRepository = anatomicalStructureRepository;
    }

    @Override
    public InstanceDataDTO createInstanceData(InstanceDataDTO instanceDataDTO) {
        UUID studyId = instanceDataDTO.getStudyId() != null ? instanceDataDTO.getStudyId() : null;
        UUID seriesId = instanceDataDTO.getSeriesId() != null ? instanceDataDTO.getSeriesId() : null;
        UUID structureId = instanceDataDTO.getStructureId() != null ? instanceDataDTO.getStructureId() : null;

        Study study = studyId != null ? studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("Study not found with ID: " + studyId)) : null;
        Series series = seriesId != null ? seriesRepository.findById(seriesId)
                .orElseThrow(() -> new EntityNotFoundException("Series not found with ID: " + seriesId)) : null;
        AnatomicalStructure anatomicalStructure = structureId != null ? anatomicalStructureRepository.findById(structureId)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructure not found with ID: " + structureId)) : null;

        InstanceData instanceData = instanceDataMapper.toEntity(instanceDataDTO);

        if (study != null) {
            instanceData.setStudy(study);
            instanceData.setStudyName(study.getName());
        }

        if (series != null) {
            instanceData.setSeries(series);
            instanceData.setSeriesName(series.getName());
            instanceData.setSeriesNumber(series.getNumber());
        }

        if (anatomicalStructure != null) {
            instanceData.setStructure(anatomicalStructure);
            instanceData.setStructureName(anatomicalStructure.getName());
        }

        InstanceData savedInstanceData = instanceDataRepository.save(instanceData);

        InstanceDataDTO responseDTO = instanceDataMapper.toDTO(savedInstanceData);

        if (study != null) {
            responseDTO.setStudyId(studyId);
            responseDTO.setStudyName(study.getName());
        }

        if (series != null) {
            responseDTO.setSeriesId(seriesId);
            responseDTO.setSeriesName(series.getName());
        }

        if (anatomicalStructure != null) {
            responseDTO.setStructureId(structureId);
            responseDTO.setStructureName(anatomicalStructure.getName());
        }

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