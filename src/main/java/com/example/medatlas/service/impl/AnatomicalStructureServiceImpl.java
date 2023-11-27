package com.example.medatlas.service.impl;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.mapper.AnatomicalStructureMapper;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import com.example.medatlas.repository.AnatomicalStructureRepository;
import com.example.medatlas.service.AnatomicalStructureService;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnatomicalStructureServiceImpl implements AnatomicalStructureService {

    private final AnatomicalStructureRepository structureRepository;
    private final AnatomicalStructureMapper structureMapper;
    private final AnatomicalStructureSubjectService subjectService;

    @Autowired
    public AnatomicalStructureServiceImpl(
            AnatomicalStructureRepository structureRepository,
            AnatomicalStructureMapper structureMapper,
            AnatomicalStructureSubjectService subjectService) {
        this.structureRepository = structureRepository;
        this.structureMapper = structureMapper;
        this.subjectService = subjectService;
    }

    @Override
    public List<AnatomicalStructureDTO> getAllAnatomicalStructures() {
        List<AnatomicalStructure> structureList = structureRepository.findAll();
        return structureMapper.toDTOList(structureList);
    }

    @Override
    public AnatomicalStructureDTO getAnatomicalStructureById(UUID id) {
        AnatomicalStructure structure = structureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructure not found with id: " + id));
        return structureMapper.toDTO(structure);
    }

    @Override
    public List<AnatomicalStructureDTO> searchAnatomicalStructures(String name, UUID anatomicalStructureSubjectId, String orderBy, String orderByDirection) {
        List<AnatomicalStructure> structureList = structureRepository.searchAnatomicalStructures(name, anatomicalStructureSubjectId, orderBy, orderByDirection);
        return structureMapper.toDTOList(structureList);
    }

    @Override
    public AnatomicalStructureDTO updateAnatomicalStructure(UUID id, AnatomicalStructureDTO structureDTO) {
        AnatomicalStructure existingStructure = structureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Structure not found with id: " + id));

        existingStructure.setName(structureDTO.getName());

        existingStructure = structureRepository.save(existingStructure);

        return structureMapper.toDTO(existingStructure);
    }

    @Override
    public void deleteAnatomicalStructure(UUID id) {
        AnatomicalStructure structure = structureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Structure not found with id: " + id));
        structureRepository.delete(structure);
    }

    @Override
    public AnatomicalStructureDTO createAnatomicalStructureWithSubject(AnatomicalStructureDTO structureDTO,
                                                                       AnatomicalStructureSubjectWithoutStructuresDTO anatomicalStructureSubjectDTO) {
        AnatomicalStructureSubjectDTO parentSubject = subjectService.getAnatomicalStructureSubjectById(anatomicalStructureSubjectDTO.getId());
        AnatomicalStructureSubjectWithoutStructuresDTO parentSubjectDTO = new AnatomicalStructureSubjectWithoutStructuresDTO();
        parentSubjectDTO.setId(parentSubject.getId());
        parentSubjectDTO.setName(parentSubject.getName());
        parentSubjectDTO.setColor(parentSubject.getColor());

        AnatomicalStructure structure = structureMapper.toEntity(structureDTO);
        structure.setAnatomicalStructureSubject(structureMapper.toEntity(parentSubjectDTO));

        structure = structureRepository.save(structure);

        AnatomicalStructureDTO resultDTO = new AnatomicalStructureDTO();
        resultDTO.setId(structure.getId());
        resultDTO.setName(structure.getName());
        resultDTO.setDescription(structure.getDescription());
        resultDTO.setAnatomicalStructureSubject(parentSubjectDTO);
        return resultDTO;
    }

    @Override
    public AnatomicalStructureSubjectWithoutStructuresDTO getAnatomicalStructureSubjectByStructureId(UUID structureId) {
        Optional<AnatomicalStructure> structureOptional = structureRepository.findById(structureId);
        if (structureOptional.isPresent()) {
            AnatomicalStructure structure = structureOptional.get();
            AnatomicalStructureSubject subject = structure.getAnatomicalStructureSubject();
            if (subject != null) {
                return structureMapper.toAnatomicalStructureSubjectDTO(subject);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}