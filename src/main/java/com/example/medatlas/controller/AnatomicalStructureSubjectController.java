package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithChildrenDTO;
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
    public ResponseEntity<AnatomicalStructureSubjectDTO> createSubject(@RequestBody AnatomicalStructureSubjectDTO subject) {
        AnatomicalStructureSubjectDTO createdSubject = subjectService.createAnatomicalStructureSubject(subject);
        return ResponseEntity.ok(createdSubject);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an anatomical structure subject by ID with children")
    public ResponseEntity<AnatomicalStructureSubjectWithChildrenDTO> getSubjectWithChildren(@PathVariable UUID id) {
        AnatomicalStructureSubjectWithChildrenDTO subjectWithChildrenDTO = subjectService.getAnatomicalStructureSubjectWithChildren(id);
        if (subjectWithChildrenDTO != null) {
            return ResponseEntity.ok(subjectWithChildrenDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Get all anatomical structure subjects")
    public ResponseEntity<List<AnatomicalStructureSubjectDTO>> getAllSubjects() {
        List<AnatomicalStructureSubjectDTO> subjectDTOList = subjectService.getAllAnatomicalStructureSubjects();
        return ResponseEntity.ok(subjectDTOList);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update an anatomical structure subject by ID")
    public ResponseEntity<AnatomicalStructureSubjectDTO> updateSubject(@PathVariable UUID id, @RequestBody AnatomicalStructureSubjectDTO subject) {
        AnatomicalStructureSubjectDTO updatedSubject = subjectService.updateAnatomicalStructureSubject(id, subject);
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