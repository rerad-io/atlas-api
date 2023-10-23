package com.example.medatlas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.service.AnatomicalStructureService;
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
    @Operation(summary = "Create an anatomical structure")
    public ResponseEntity<AnatomicalStructureDTO> createStructure(@RequestBody AnatomicalStructureDTO structureDTO) {
        AnatomicalStructureDTO createdStructure = structureService.createAnatomicalStructure(structureDTO);
        return ResponseEntity.ok(createdStructure);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an anatomical structure by ID")
    public ResponseEntity<AnatomicalStructureDTO> getStructureById(@PathVariable UUID id) {
        AnatomicalStructureDTO structureDTO = structureService.getAnatomicalStructureById(id);
        if (structureDTO != null) {
            return ResponseEntity.ok(structureDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
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