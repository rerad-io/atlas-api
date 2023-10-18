package com.example.medatlas.service.impl;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.mapper.AnatomicalStructureSubjectMapper;
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

    @Autowired
    public AnatomicalStructureSubjectServiceImpl(
            AnatomicalStructureSubjectRepository subjectRepository,
            AnatomicalStructureSubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public AnatomicalStructureSubjectDTO createAnatomicalStructureSubject(AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubject subject = subjectMapper.toEntity(subjectDTO);
        subject = subjectRepository.save(subject);
        subjectDTO.setId(subject.getId()); // Устанавливаем id в объекте subjectDTO
        return subjectMapper.toDTO(subject);
    }

    @Override
    public AnatomicalStructureSubjectDTO getAnatomicalStructureSubjectById(UUID id) {
        AnatomicalStructureSubject subject = subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        return subjectMapper.toDTO(subject);
    }

    @Override
    public List<AnatomicalStructureSubjectDTO> getAllAnatomicalStructureSubjects() {
        List<AnatomicalStructureSubject> subjectsList = subjectRepository.findAll();
        return subjectsList.stream().map(subjectMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AnatomicalStructureSubjectDTO updateAnatomicalStructureSubject(UUID id, AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubject existingSubject = subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        AnatomicalStructureSubject updatedSubject = subjectMapper.toEntity(subjectDTO);
        updatedSubject.setId(existingSubject.getId());
        updatedSubject = subjectRepository.save(updatedSubject);
        return subjectMapper.toDTO(updatedSubject);
    }

    @Override
    public void deleteAnatomicalStructureSubject(UUID id) {
        AnatomicalStructureSubject subject = subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AnatomicalStructureSubject not found with id: " + id));
        subjectRepository.delete(subject);
    }
}