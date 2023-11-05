package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/AnatomicalStructureSubject")
@Api(value = "Anatomical Structure Subject API", tags = {"API endpoints for the Anatomical Structure Subject Controller"})
//@Tag(name = "Anatomical Structure Subject API", description = "API endpoints for the Anatomical Structure Subject Controller")
public class AnatomicalStructureSubjectController {

    private final AnatomicalStructureSubjectService subjectService;

    @Autowired
    public AnatomicalStructureSubjectController(AnatomicalStructureSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/")
    @Operation(summary = "Create an anatomical structure subject")
    public ResponseEntity<AnatomicalStructureSubjectWithoutStructuresDTO> createSubject(@RequestBody AnatomicalStructureSubjectDTO subject) {
        AnatomicalStructureSubjectWithoutStructuresDTO createdSubject = subjectService.createAnatomicalStructureSubject(subject);
        return ResponseEntity.ok(createdSubject);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get an anatomical structure subject by ID with children")
    public ResponseEntity<AnatomicalStructureSubjectDTO> getSubjectWithChildren(@PathVariable UUID id) {
        AnatomicalStructureSubjectDTO subjectWithChildrenDTO = subjectService.getAnatomicalStructureSubjectWithChildren(id);
        if (subjectWithChildrenDTO != null) {
            return ResponseEntity.ok(subjectWithChildrenDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all anatomical structure subjects")
    public ResponseEntity<List<AnatomicalStructureSubjectWithoutStructuresDTO>> getAllSubjects() {
        List<AnatomicalStructureSubjectWithoutStructuresDTO> subjectDTOList = subjectService.getAllAnatomicalStructureSubjects();
        return ResponseEntity.ok(subjectDTOList);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an anatomical structure subject by ID")
    public ResponseEntity<Void> deleteSubject(@PathVariable UUID id) {
        subjectService.deleteAnatomicalStructureSubject(id);
        return ResponseEntity.noContent().build();
    }
}