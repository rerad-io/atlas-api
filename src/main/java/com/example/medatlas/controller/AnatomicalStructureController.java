package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.dto.AnatomicalStructureWithSubjectDTO;
import com.example.medatlas.service.AnatomicalStructureService;
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

    @Autowired
    public AnatomicalStructureController(AnatomicalStructureService structureService) {
        this.structureService = structureService;
    }

    @PostMapping("/")
    @Operation(summary = "Create an anatomical structure with parent subject")
    public ResponseEntity<AnatomicalStructureWithSubjectDTO> createStructureWithSubject(@RequestBody AnatomicalStructureDTO requestDTO) {
        AnatomicalStructureWithSubjectDTO createdStructure = structureService.createAnatomicalStructureWithSubject(requestDTO);
        return ResponseEntity.ok(createdStructure);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an anatomical structure by ID")
    public ResponseEntity<AnatomicalStructureWithSubjectDTO> getStructureById(@PathVariable UUID id) {
        AnatomicalStructureWithSubjectDTO structureDTO = structureService.getAnatomicalStructureById(id);
        if (structureDTO != null) {
            AnatomicalStructureSubjectDTO subject = structureService.getAnatomicalStructureSubjectByStructureId(id);
            if (subject != null) {
                structureDTO.setId(structureDTO.getId());
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
    public ResponseEntity<AnatomicalStructureWithSubjectDTO> updateStructure(@PathVariable UUID id, @RequestBody AnatomicalStructureWithSubjectDTO structureDTO) {
        AnatomicalStructureWithSubjectDTO updatedStructure = structureService.updateAnatomicalStructure(id, structureDTO);
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