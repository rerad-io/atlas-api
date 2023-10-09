package com.example.medatlas.service.impl;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.mapper.InstanceDataMapper;
import com.example.medatlas.model.InstanceData;
import com.example.medatlas.repository.InstanceDataRepository;
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

    @Autowired
    public InstanceDataServiceImpl(
            InstanceDataRepository instanceDataRepository,
            InstanceDataMapper instanceDataMapper) {
        this.instanceDataRepository = instanceDataRepository;
        this.instanceDataMapper = instanceDataMapper;
    }

    @Override
    public InstanceDataDTO createInstanceData(InstanceDataDTO instanceDataDTO) {
        InstanceData instanceData = instanceDataMapper.toEntity(instanceDataDTO);
        InstanceData savedInstanceData = instanceDataRepository.save(instanceData);
        return instanceDataMapper.toDTO(savedInstanceData);
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

        // Update existingInstanceData with values from instanceDataDTO

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