package com.example.medatlas.controller;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.service.SeriesService;
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
@RequestMapping("/api/Series")
@Api(value = "Series API", tags = {"API endpoints for the Series Controller"})
public class SeriesController {

    private final SeriesService seriesService;
    private final StudyService studyService;

    @Autowired
    public SeriesController(SeriesService seriesService, StudyService studyService) {
        this.seriesService = seriesService;
        this.studyService = studyService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create an series")
    public ResponseEntity<SeriesDTO> createSeries(@RequestBody SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());

        if (parentStudy == null) {
            return ResponseEntity.notFound().build();
        }

        SeriesDTO createdSeries = seriesService.createSeries(seriesDTO);
        return ResponseEntity.ok(createdSeries);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all series")
    public ResponseEntity<List<SeriesDTOWithoutStudy>> getAllSeries() {
        List<SeriesDTOWithoutStudy> seriesDTOList = seriesService.getAllSeries();
        return ResponseEntity.ok(seriesDTOList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get series by ID")
    public ResponseEntity<SeriesDTO> getSeriesById(@PathVariable UUID id) {
        SeriesDTO seriesDTO = seriesService.getSeriesById(id);
        if (seriesDTO != null) {
            List<InstanceDataDTO> instanceDataDTOList = seriesService.getInstanceDataForSeries(id);
            seriesDTO.setInstanceDataList(instanceDataDTOList);
            StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());
            if(parentStudy != null){
                seriesDTO.setStudyExternalId(parentStudy.getExternalId());
            }
            return ResponseEntity.ok(seriesDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an anatomical series")
    public ResponseEntity<SeriesDTO> updateSeries(@PathVariable UUID id, @RequestBody SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());

        if (parentStudy == null) {
            return ResponseEntity.notFound().build();
        }
        SeriesDTO updatedSeries = seriesService.updateSeries(id, seriesDTO);
        return ResponseEntity.ok(updatedSeries);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an anatomical series")
    public ResponseEntity<Void> deleteSeries(@PathVariable UUID id) {
        seriesService.deleteSeries(id);
        return ResponseEntity.noContent().build();
    }
}