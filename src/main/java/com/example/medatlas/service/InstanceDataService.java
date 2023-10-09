package com.example.medatlas.service;

import com.example.medatlas.dto.InstanceDataDTO;

import java.util.List;
import java.util.UUID;

public interface InstanceDataService {
    InstanceDataDTO createInstanceData(InstanceDataDTO instanceDataDTO);

    InstanceDataDTO getInstanceDataById(UUID id);

    List<InstanceDataDTO> getAllInstanceData();

    InstanceDataDTO updateInstanceData(UUID id, InstanceDataDTO instanceDataDTO);

    void deleteInstanceData(UUID id);
}