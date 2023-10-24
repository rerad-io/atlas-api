package com.example.medatlas.controller;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.service.SeriesService;
import com.example.medatlas.service.StudyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Series")
@Tag(name = "Anatomical Series API", description = "API endpoints for the Series Controller")
public class SeriesController {

    private final SeriesService seriesService;
    private final StudyService studyService;

    @Autowired
    public SeriesController(SeriesService seriesService, StudyService studyService) {
        this.seriesService = seriesService;
        this.studyService = studyService;
    }

    @PostMapping("/")
    @Operation(summary = "Create an anatomical series")
    public ResponseEntity<SeriesDTO> createSeries(@RequestBody SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudy().getId());

        if (parentStudy == null) {
            return ResponseEntity.notFound().build();
        }

        SeriesDTO createdSeries = seriesService.createSeries(seriesDTO);
        createdSeries.setStudy(parentStudy);

        return ResponseEntity.ok(createdSeries);
    }

    @GetMapping("/")
    @Operation(summary = "Get all anatomical series")
    public ResponseEntity<List<SeriesDTO>> getAllSeries() {
        List<SeriesDTO> seriesDTOList = seriesService.getAllSeries();

        seriesDTOList.forEach(seriesDTO -> {
            StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudy().getId());
            seriesDTO.setStudy(parentStudy);
        });
        return ResponseEntity.ok(seriesDTOList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get anatomical series by ID")
    public ResponseEntity<SeriesDTO> getSeriesById(@PathVariable UUID id) {
        SeriesDTO seriesDTO = seriesService.getSeriesById(id);
        if (seriesDTO != null) {
            // Загружаем информацию о родительской сущности Study
            StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudy().getId());
            seriesDTO.setStudy(parentStudy);
            return ResponseEntity.ok(seriesDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an anatomical series")
    public ResponseEntity<SeriesDTO> updateSeries(@PathVariable UUID id, @RequestBody SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudy().getId());

        if (parentStudy == null) {
            return ResponseEntity.notFound().build();
        }
        SeriesDTO updatedSeries = seriesService.updateSeries(id, seriesDTO);
        updatedSeries.setStudy(parentStudy);
        return ResponseEntity.ok(updatedSeries);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an anatomical series")
    public ResponseEntity<Void> deleteSeries(@PathVariable UUID id) {
        seriesService.deleteSeries(id);
        return ResponseEntity.noContent().build();
    }
}