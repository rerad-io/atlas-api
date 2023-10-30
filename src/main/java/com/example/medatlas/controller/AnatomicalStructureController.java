package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.mapper.AnatomicalStructureMapper;
import com.example.medatlas.service.AnatomicalStructureService;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/AnatomicalStructure")
@Tag(name = "Anatomical Structure API", description = "API endpoints for the Anatomical Structure Controller")
public class AnatomicalStructureController {

    private final AnatomicalStructureService structureService;
    private final AnatomicalStructureSubjectService subjectService;
    private final AnatomicalStructureMapper structureMapper;

    @Autowired
    public AnatomicalStructureController(AnatomicalStructureService structureService, AnatomicalStructureSubjectService subjectService, AnatomicalStructureMapper structureMapper) {
        this.structureService = structureService;
        this.subjectService = subjectService;
        this.structureMapper = structureMapper;
    }

    @PostMapping("/")
    @Operation(summary = "Create an anatomical structure with parent subject")
    public ResponseEntity<AnatomicalStructureDTO> createStructureWithSubject(@RequestBody AnatomicalStructureDTO requestDTO) {
        AnatomicalStructureSubjectDTO anatomicalStructureSubject = requestDTO.getAnatomicalStructureSubject(); // Получаем данные о родительской сущности из DTO
        AnatomicalStructureDTO createdStructure = structureService.createAnatomicalStructureWithSubject(requestDTO, anatomicalStructureSubject); // Передаем anatomicalStructureSubject вместо subject
        return ResponseEntity.ok(createdStructure);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an anatomical structure by ID")
    public ResponseEntity<AnatomicalStructureDTO> getStructureById(@PathVariable UUID id) {
        AnatomicalStructureDTO structureDTO = structureService.getAnatomicalStructureById(id);
        if (structureDTO != null) {
            AnatomicalStructureSubjectDTO anatomicalStructureSubject = structureService.getAnatomicalStructureSubjectByStructureId(id);
            if (anatomicalStructureSubject != null) {
                structureDTO.setAnatomicalStructureSubject(anatomicalStructureSubject); // Устанавливаем родительскую сущность в DTO
            }
            return ResponseEntity.ok(structureDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    @Operation(summary = "Get all anatomical structures")
    public ResponseEntity<List<AnatomicalStructureDTO>> getAllAnatomicalStructures() {
        List<AnatomicalStructureDTO> structureDTOList = structureService.getAllAnatomicalStructures();
        return ResponseEntity.ok(structureDTOList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an anatomical structure by ID")
    public ResponseEntity<AnatomicalStructureDTO> updateStructure(@PathVariable UUID id, @RequestBody AnatomicalStructureDTO structureDTO) {
        AnatomicalStructureDTO updatedStructure = structureService.updateAnatomicalStructure(id, structureDTO);
        if (updatedStructure != null) {
            return ResponseEntity.ok(updatedStructure);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an anatomical structure by ID")
    public ResponseEntity<Void> deleteStructure(@PathVariable UUID id) {
        structureService.deleteAnatomicalStructure(id);
        return ResponseEntity.noContent().build();
    }
}