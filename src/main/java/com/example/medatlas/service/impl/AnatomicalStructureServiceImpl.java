package com.example.medatlas.service.impl;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.mapper.AnatomicalStructureMapper;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.repository.AnatomicalStructureRepository;
import com.example.medatlas.service.AnatomicalStructureService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnatomicalStructureServiceImpl implements AnatomicalStructureService {

    private final AnatomicalStructureRepository structureRepository;
    private final AnatomicalStructureMapper structureMapper;

    @Autowired
    public AnatomicalStructureServiceImpl(
            AnatomicalStructureRepository structureRepository,
            AnatomicalStructureMapper structureMapper) {
        this.structureRepository = structureRepository;
        this.structureMapper = structureMapper;
    }

    @Override
    public AnatomicalStructureDTO createStructure(AnatomicalStructureDTO structureDTO) {
        AnatomicalStructure structure = structureMapper.toEntity(structureDTO);
        structure = structureRepository.save(structure);
        return structureMapper.toDTO(structure);
    }

    @Override
    public AnatomicalStructureDTO getStructureById(UUID id) {
        AnatomicalStructure structure = structureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructure not found with id: " + id));
        return structureMapper.toDTO(structure);
    }

    @Override
    public List<AnatomicalStructureDTO> getAllStructures() {
        List<AnatomicalStructure> structureList = structureRepository.findAll();
        return structureMapper.toDTOList(structureList);
    }

    @Override
    public AnatomicalStructureDTO updateStructure(UUID id, AnatomicalStructureDTO structureDTO) {
        AnatomicalStructure existingStructure = structureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Structure not found with id: " + id));

        // Update existingStructure with values from structureDTO

        AnatomicalStructure updatedStructure = structureRepository.save(existingStructure);
        return structureMapper.toDTO(updatedStructure);
    }

    @Override
    public void deleteStructure(UUID id) {
        AnatomicalStructure structure = structureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Structure not found with id: " + id));
        structureRepository.delete(structure);
    }
}