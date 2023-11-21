package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.exception.DuplicateColorException;
import com.example.medatlas.exception.DuplicateNameException;
import com.example.medatlas.exception.NestedStructuresException;
import com.example.medatlas.exception.SubjectNotFoundException;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/AnatomicalStructureSubject")
@Api(value = "Anatomical Structure Subject API", tags = {"API endpoints for the Anatomical Structure Subject Controller"})
public class AnatomicalStructureSubjectController {

    private final AnatomicalStructureSubjectService subjectService;

    @Autowired
    public AnatomicalStructureSubjectController(AnatomicalStructureSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/")
    @Operation(summary = "Create an anatomical structure subject")
    public ResponseEntity<?> createSubject(@RequestBody AnatomicalStructureSubjectDTO subject) {
        try {
            AnatomicalStructureSubjectWithoutStructuresDTO createdSubject = subjectService.createAnatomicalStructureSubject(subject);
            return ResponseEntity.ok(createdSubject);
        } catch (DuplicateNameException | DuplicateColorException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all anatomical structure subjects")
    public ResponseEntity<List<AnatomicalStructureSubjectWithoutStructuresDTO>> getAllSubjects() {
        List<AnatomicalStructureSubjectWithoutStructuresDTO> subjectDTOList = subjectService.getAllAnatomicalStructureSubjects();
        return ResponseEntity.ok(subjectDTOList);
    }

    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search anatomical structure subject by name")
    public ResponseEntity<List<AnatomicalStructureSubjectDTO>> searchAnatomicalStructureSubjectByName(@RequestParam("name") String name) {
        List<AnatomicalStructureSubjectDTO> subjectDTOList = subjectService.getAnatomicalStructureSubjectsByName(name);
        return ResponseEntity.ok(subjectDTOList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get an anatomical structure subject by ID with children")
    public ResponseEntity<?> getSubjectWithChildren(@PathVariable UUID id) {
        try {
            AnatomicalStructureSubjectDTO subjectWithChildrenDTO = subjectService.getAnatomicalStructureSubjectWithChildren(id);
            if (subjectWithChildrenDTO != null) {
                return ResponseEntity.ok(subjectWithChildrenDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
            }
        } catch (SubjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an anatomical structure subject by ID")
    public ResponseEntity<?> updateSubject(@PathVariable UUID id, @RequestBody AnatomicalStructureSubjectDTO subject) {
        try {
            AnatomicalStructureSubjectDTO updatedSubject = subjectService.updateAnatomicalStructureSubject(id, subject);
            if (updatedSubject != null) {
                return ResponseEntity.ok(updatedSubject);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
            }
        } catch (SubjectNotFoundException | DuplicateColorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an anatomical structure subject by ID")
    public ResponseEntity<?> deleteSubject(@PathVariable UUID id) {
        try {
            if (subjectService.hasChildStructures(id)) {
                throw new NestedStructuresException("Deletion is not possible. The subject contains nested Anatomical Structures.");
            }

            subjectService.deleteAnatomicalStructureSubject(id);
            return ResponseEntity.noContent().build();
        } catch (NestedStructuresException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}