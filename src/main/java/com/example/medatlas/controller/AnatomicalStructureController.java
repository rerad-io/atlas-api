package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.AnatomicalStructureSubjectWithoutStructuresDTO;
import com.example.medatlas.service.AnatomicalStructureService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/AnatomicalStructure")
@Api(value = "Anatomical Structure API", tags = {"API endpoints for the Anatomical Structure Controller" })
public class AnatomicalStructureController {

    private final AnatomicalStructureService structureService;
    private static final Logger log = LoggerFactory.getLogger(AnatomicalStructureController.class);

    @Autowired
    public AnatomicalStructureController(AnatomicalStructureService structureService) {
        this.structureService = structureService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create an anatomical structure with parent subject")
    public ResponseEntity<AnatomicalStructureDTO> createStructureWithSubject(@RequestBody AnatomicalStructureDTO requestDTO) {
        AnatomicalStructureSubjectWithoutStructuresDTO anatomicalStructureSubject = requestDTO.getAnatomicalStructureSubject();
        AnatomicalStructureDTO createdStructure = structureService.createAnatomicalStructureWithSubject(requestDTO, anatomicalStructureSubject);
        return ResponseEntity.ok(createdStructure);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get an anatomical structure by ID")
    public ResponseEntity<AnatomicalStructureDTO> getStructureById(@PathVariable UUID id) {
        AnatomicalStructureDTO structureDTO = structureService.getAnatomicalStructureById(id);
        if (structureDTO != null) {
            AnatomicalStructureSubjectWithoutStructuresDTO anatomicalStructureSubject = structureService.getAnatomicalStructureSubjectByStructureId(id);
            if (anatomicalStructureSubject != null) {
                structureDTO.setAnatomicalStructureSubject(anatomicalStructureSubject);
            }
            return ResponseEntity.ok(structureDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all anatomical structures")
    public ResponseEntity<List<AnatomicalStructureDTO>> getAllAnatomicalStructures() {
        List<AnatomicalStructureDTO> structureDTOList = structureService.getAllAnatomicalStructures();
        return ResponseEntity.ok(structureDTOList);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search anatomical structures by name")
    public ResponseEntity<List<AnatomicalStructureDTO>> searchAnatomicalStructures(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "anatomicalStructureSubjectId", required = false) UUID anatomicalStructureSubjectId,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "orderByDirection", defaultValue = "asc") String orderByDirection) {

        log.info("Search request parameters: name={}, anatomicalStructureSubjectId={}, orderBy={}, orderByDirection={}",
                name, anatomicalStructureSubjectId, orderBy, orderByDirection);

        List<AnatomicalStructureDTO> structureDTOList = structureService.searchAnatomicalStructures(
                name,
                anatomicalStructureSubjectId,
                orderBy,
                orderByDirection
        );

        if (structureDTOList.isEmpty()) {
            throw new EntityNotFoundException("No anatomical structures found with the specified parameters");
        }

        return ResponseEntity.ok(structureDTOList);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an anatomical structure by ID")
    public ResponseEntity<Void> deleteStructure(@PathVariable UUID id) {
        structureService.deleteAnatomicalStructure(id);
        return ResponseEntity.noContent().build();
    }
}