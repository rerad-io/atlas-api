package com.example.medatlas.service.impl;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.dto.AnatomicalStructureWithoutSubjectDTO;
import com.example.medatlas.mapper.AnatomicalStructureMapper;
import com.example.medatlas.mapper.AnatomicalStructureSubjectMapper;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import com.example.medatlas.repository.AnatomicalStructureSubjectRepository;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnatomicalStructureSubjectServiceImpl implements AnatomicalStructureSubjectService {

    private final AnatomicalStructureSubjectRepository subjectRepository;
    private final AnatomicalStructureSubjectMapper subjectMapper;
    private final AnatomicalStructureMapper structureMapper;
    private final AnatomicalStructureSubjectMapper subjectWithoutStructureMapper;

    @Autowired
    public AnatomicalStructureSubjectServiceImpl(
            AnatomicalStructureSubjectRepository subjectRepository,
            AnatomicalStructureSubjectMapper subjectMapper,
            AnatomicalStructureMapper structureMapper, AnatomicalStructureSubjectMapper subjectWithoutStructureMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
        this.structureMapper = structureMapper;
        this.subjectWithoutStructureMapper = subjectWithoutStructureMapper;
    }

    @Override
    public AnatomicalStructureSubjectWithoutStructuresDTO createAnatomicalStructureSubject(AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubject subject = subjectMapper.toEntity(subjectDTO);
        subject = subjectRepository.save(subject);

        AnatomicalStructureSubjectWithoutStructuresDTO resultDTO = new AnatomicalStructureSubjectWithoutStructuresDTO();
        resultDTO.setId(subject.getId());
        resultDTO.setName(subject.getName());
        resultDTO.setColor(subject.getColor());

        return resultDTO;
    }

    @Override
    public List<AnatomicalStructureSubjectWithoutStructuresDTO> getAllAnatomicalStructureSubjects() {
        List<AnatomicalStructureSubject> subjectsList = subjectRepository.findAll();
        return subjectsList.stream()
                .map(subjectWithoutStructureMapper::toAnatomicalStructureSubjectWithoutStructuresDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnatomicalStructureSubjectDTO> getAnatomicalStructureSubjectsByName(String name) {
        List<AnatomicalStructureSubject> subjectList = subjectRepository.findByNameIgnoreCase(name);
        return subjectMapper.toDTOList(subjectList);
    }
    @Override
    public AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectById(UUID id) {
        AnatomicalStructureSubject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        return subjectMapper.toDTO(subject);
    }

    @Override
    public AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectWithChildren(UUID id) {
        AnatomicalStructureSubjectDTO subject = getAnatomicalStructureSubjectById(id);

        List<AnatomicalStructureWithoutSubjectDTO> childrenDTO = getChildrenBySubjectId(id);

        AnatomicalStructureSubjectDTO subjectWithChildrenDTO = new AnatomicalStructureSubjectDTO();
        subjectWithChildrenDTO.setId(subject.getId());
        subjectWithChildrenDTO.setName(subject.getName());
        subjectWithChildrenDTO.setColor(subject.getColor());
        subjectWithChildrenDTO.setAnatomicalStructures(childrenDTO);

        return subjectWithChildrenDTO;
    }

    @Override
    public List<AnatomicalStructureWithoutSubjectDTO> getChildrenBySubjectId(UUID subjectId) {
        AnatomicalStructureSubject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + subjectId));

        List<AnatomicalStructure> children = subject.getAnatomicalStructures();

        return children.stream()
                .map(structureMapper::toAnatomicalStructureWithoutSubjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnatomicalStructureSubjectDTO updateAnatomicalStructureSubject(UUID id, AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        AnatomicalStructureSubject updatedSubject = subjectMapper.toEntity(subjectDTO);
        updatedSubject.setId(existingSubject.getId());
        updatedSubject = subjectRepository.save(updatedSubject);
        return subjectMapper.toDTO(updatedSubject);
    }

    @Override
    public void deleteAnatomicalStructureSubject(UUID id) {
        AnatomicalStructureSubject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        subjectRepository.delete(subject);
    }

    @Override
    public boolean hasChildStructures(UUID id) {
        AnatomicalStructureSubject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));

        List<AnatomicalStructure> childStructures = subject.getAnatomicalStructures();

        return !childStructures.isEmpty();
    }
}