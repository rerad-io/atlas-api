package com.example.medatlas.service.impl;

import com.example.medatlas.dto.*;
import com.example.medatlas.mapper.AnatomicalStructureMapper;
import com.example.medatlas.mapper.AnatomicalStructureSubjectMapper;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import com.example.medatlas.repository.AnatomicalStructureSubjectRepository;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class AnatomicalStructureSubjectServiceImpl implements AnatomicalStructureSubjectService {

    private final AnatomicalStructureSubjectRepository subjectRepository;
    private final AnatomicalStructureSubjectMapper subjectMapper;
    private final AnatomicalStructureMapper anatomicalStructureMapper;

    @Autowired
    public AnatomicalStructureSubjectServiceImpl(
            AnatomicalStructureSubjectRepository subjectRepository,
            AnatomicalStructureSubjectMapper subjectMapper, AnatomicalStructureMapper anatomicalStructureMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
        this.anatomicalStructureMapper = anatomicalStructureMapper;
    }

    @Override
    public AnatomicalStructureSubjectWithChildrenDTO createAnatomicalStructureSubject(AnatomicalStructureSubjectWithChildrenDTO subjectDTO) {
        AnatomicalStructureSubject subject = subjectMapper.toEntity(subjectDTO);
        subject = subjectRepository.save(subject);
        subjectDTO.setId(subject.getId());
        return subjectDTO;
    }


    @Override
    public AnatomicalStructureSubjectWithChildrenDTO getAnatomicalStructureSubjectById(UUID id) {
        AnatomicalStructureSubject subject = subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        return subjectMapper.toDTO(subject);
    }

    @Override
    public AnatomicalStructureSubjectWithChildrenDTO getAnatomicalStructureSubjectWithChildren(UUID id) {
        AnatomicalStructureSubjectWithChildrenDTO subject = getAnatomicalStructureSubjectById(id);

        // Получите детей для данного субъекта
        List<AnatomicalStructureDTO> childrenDTO = getChildrenBySubjectId(id);

        // Создайте AnatomicalStructureSubjectWithChildrenDTO и установите поля
        AnatomicalStructureSubjectWithChildrenDTO subjectWithChildrenDTO = new AnatomicalStructureSubjectWithChildrenDTO();
        subjectWithChildrenDTO.setId(subject.getId());
        subjectWithChildrenDTO.setName(subject.getName());
        subjectWithChildrenDTO.setColor(subject.getColor());
        subjectWithChildrenDTO.setAnatomicalStructures(childrenDTO);

        return subjectWithChildrenDTO;
    }

    @Override
    public List<AnatomicalStructureDTO> getChildrenBySubjectId(UUID subjectId) {
        AnatomicalStructureSubject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + subjectId));

        List<AnatomicalStructure> children = subject.getAnatomicalStructures();

        return children.stream()
                .map(anatomicalStructure -> {
                    AnatomicalStructureDTO dto = new AnatomicalStructureDTO();
                    dto.setId(anatomicalStructure.getId());
                    dto.setName(anatomicalStructure.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AnatomicalStructureSubjectDTO> getAllAnatomicalStructureSubjects() {
        List<AnatomicalStructureSubject> subjectsList = subjectRepository.findAll();
        return subjectsList.stream()
                .map(subjectMapper::toDTO)
                .map(dto -> {
                    AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
                    subjectDTO.setId(dto.getId());
                    subjectDTO.setName(dto.getName());
                    subjectDTO.setColor(dto.getColor());
                    return subjectDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public AnatomicalStructureSubjectWithChildrenDTO updateAnatomicalStructureSubject(UUID id, AnatomicalStructureSubjectWithChildrenDTO subject) {
        AnatomicalStructureSubject existingSubject = subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        AnatomicalStructureSubject updatedSubject = subjectMapper.toEntity(subject);
        updatedSubject.setId(existingSubject.getId());
        updatedSubject = subjectRepository.save(updatedSubject);
        return subjectMapper.toDTO(updatedSubject);
    }

    @Override
    public void deleteAnatomicalStructureSubject(UUID id) {
        AnatomicalStructureSubject subject = subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        subjectRepository.delete(subject);
    }

    @Override
    public boolean existsSubjectById(UUID id) {
        return subjectRepository.existsById(id);
    }
}