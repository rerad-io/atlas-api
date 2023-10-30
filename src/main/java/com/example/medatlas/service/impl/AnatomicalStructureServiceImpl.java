package com.example.medatlas.service.impl;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.mapper.AnatomicalStructureMapper;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import com.example.medatlas.repository.AnatomicalStructureRepository;
import com.example.medatlas.repository.AnatomicalStructureSubjectRepository;
import com.example.medatlas.service.AnatomicalStructureService;
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
    private final AnatomicalStructureSubjectRepository subjectRepository;

    @Autowired
    public AnatomicalStructureServiceImpl(
            AnatomicalStructureRepository structureRepository,
            AnatomicalStructureMapper structureMapper, AnatomicalStructureSubjectRepository subjectRepository) {
        this.structureRepository = structureRepository;
        this.structureMapper = structureMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public AnatomicalStructureDTO createAnatomicalStructure(AnatomicalStructureSubjectDTO structureDTO) {
        AnatomicalStructure structure = structureMapper.toEntity(structureDTO);
        structure = structureRepository.save(structure);
        return structureMapper.toDTO(structure);
    }

    @Override
    public AnatomicalStructureDTO getAnatomicalStructureById(UUID id) {
        AnatomicalStructure structure = structureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructure not found with id: " + id));
        return structureMapper.toDTO(structure);
    }

    @Override
    public List<AnatomicalStructureDTO> getAllAnatomicalStructures() {
        List<AnatomicalStructure> structureList = structureRepository.findAll();
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
    public AnatomicalStructureDTO createAnatomicalStructureWithSubject(AnatomicalStructureDTO structureDTO, AnatomicalStructureSubjectDTO anatomicalStructureSubject) {
        AnatomicalStructureSubject parentSubject = getParentSubject(anatomicalStructureSubject);

        AnatomicalStructure structure = createAnatomicalStructure(structureDTO, parentSubject);

        return createResultDTO(structure, parentSubject);
    }

    private AnatomicalStructureSubject getParentSubject(AnatomicalStructureSubjectDTO subjectDTO) {
        if (subjectDTO == null) {
            return null;
        }

        UUID subjectId = subjectDTO.getId();
        Optional<AnatomicalStructureSubject> parentOptional = subjectRepository.findById(subjectId);

        return parentOptional.orElseThrow(() -> new EntityNotFoundException("Parent AnatomicalStructureSubject not found with ID: " + subjectId));
    }

    private AnatomicalStructure createAnatomicalStructure(AnatomicalStructureDTO structureDTO, AnatomicalStructureSubject parentSubject) {
        AnatomicalStructure structure = structureMapper.toEntity(structureDTO);
        structure.setAnatomicalStructureSubject(parentSubject);
        return structureRepository.save(structure);
    }

    private AnatomicalStructureDTO createResultDTO(AnatomicalStructure structure, AnatomicalStructureSubject parentSubject) {
        AnatomicalStructureDTO resultDTO = new AnatomicalStructureDTO();
        resultDTO.setId(structure.getId());
        resultDTO.setName(structure.getName());

        if (parentSubject != null) {
            AnatomicalStructureSubjectDTO parentSubjectDTO = new AnatomicalStructureSubjectDTO();
            parentSubjectDTO.setId(parentSubject.getId());
            parentSubjectDTO.setName(parentSubject.getName());
            parentSubjectDTO.setColor(parentSubject.getColor());
            resultDTO.setAnatomicalStructureSubject(parentSubjectDTO);
        }
        return resultDTO;
    }

    @Override
    public AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectByStructureId(UUID structureId) {
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