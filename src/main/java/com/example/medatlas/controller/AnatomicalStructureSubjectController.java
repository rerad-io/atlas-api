package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/AnatomicalStructureSubject")
@Tag(name = "Anatomical Structure Subject API", description = "API endpoints for the Anatomical Structure Subject Controller")
public class AnatomicalStructureSubjectController {

    private final AnatomicalStructureSubjectService subjectService;

    @Autowired
    public AnatomicalStructureSubjectController(AnatomicalStructureSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/")
    @Operation(summary = "Create an anatomical structure subject")
    public ResponseEntity<AnatomicalStructureSubjectDTO> createSubject(@RequestBody AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubjectDTO createdSubject = subjectService.createAnatomicalStructureSubject(subjectDTO);
        return ResponseEntity.ok(createdSubject);
    }

    @GetMapping()
    @Operation(summary = "Get all anatomical structure subjects")
    public ResponseEntity<List<AnatomicalStructureSubjectDTO>> getSubjectAll() {
        List<AnatomicalStructureSubjectDTO> subjectDTOList = subjectService.getAllAnatomicalStructureSubjects();
        return ResponseEntity.ok(subjectDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnatomicalStructureSubjectDTO> getSubjectWithChildren(@PathVariable UUID id) {
        // Проверяем, существует ли объект AnatomicalStructureSubject с указанным id в БД
        if (!subjectService.existsSubjectById(id)) {
            return ResponseEntity.notFound().build();
        }

        AnatomicalStructureSubjectDTO subjectDTO = subjectService.getAnatomicalStructureSubjectById(id);

        List<AnatomicalStructureDTO> childrenDTO = subjectService.getChildrenBySubjectId(id);

        subjectDTO.setAnatomicalStructures(childrenDTO);

        return ResponseEntity.ok(subjectDTO);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update an anatomical structure subject by ID")
    public ResponseEntity<AnatomicalStructureSubjectDTO> updateSubject(@PathVariable UUID id, @RequestBody AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubjectDTO updatedSubject = subjectService.updateAnatomicalStructureSubject(id, subjectDTO);
        if (updatedSubject != null) {
            return ResponseEntity.ok(updatedSubject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an anatomical structure subject by ID")
    public ResponseEntity<Void> deleteSubject(@PathVariable UUID id) {
        subjectService.deleteAnatomicalStructureSubject(id);
        return ResponseEntity.noContent().build();
    }
}