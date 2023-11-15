package com.example.medatlas.controller;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.service.StudyService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Study")
@Api(value = "Study API", tags = {"API endpoints for the Study Controller"})
public class StudyController {

    private final StudyService studyService;

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a study")
    public ResponseEntity<StudyDTO> createStudy(@RequestBody StudyDTO studyDTO) {
        StudyDTO createdStudy = studyService.createStudy(studyDTO);
        return ResponseEntity.ok(createdStudy);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all studies")
    public ResponseEntity<List<StudyDTO>> getAllStudies() {
        List<StudyDTO> studyDTOList = studyService.getAllStudies();
        return ResponseEntity.ok(studyDTOList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a study by ID")
    public ResponseEntity<StudyDTO> getStudyById(@PathVariable UUID id) {
        List<SeriesDTOWithoutStudy> seriesDTOList = studyService.getSeriesForStudy(id);
        StudyDTO studyDTO = studyService.getStudyById(id);

        if (studyDTO != null) {
            List<InstanceDataDTO> instanceDataList = studyService.getInstanceDataForStudy(id);
            studyDTO.setInstanceDataList(instanceDataList);
            studyDTO.setSeriesList(seriesDTOList);
            return ResponseEntity.ok(studyDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a study")
    public ResponseEntity<StudyDTO> updateStudy(@PathVariable UUID id, @RequestBody StudyDTO studyDTO) {
        StudyDTO updatedStudy = studyService.updateStudy(id, studyDTO);
        if (updatedStudy != null) {
            return ResponseEntity.ok(updatedStudy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a study")
    public ResponseEntity<Void> deleteStudy(@PathVariable UUID id) {
        studyService.deleteStudy(id);
        return ResponseEntity.noContent().build();
    }
}