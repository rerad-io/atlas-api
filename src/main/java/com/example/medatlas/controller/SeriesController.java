package com.example.medatlas.controller;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.service.SeriesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Series")
@Api(tags = "Series API")
public class SeriesController {

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping("/")
    public ResponseEntity<SeriesDTO> createSeries(@RequestBody SeriesDTO seriesDTO) {
        SeriesDTO createdSeries = seriesService.createSeries(seriesDTO);
        return ResponseEntity.ok(createdSeries);
    }

    @GetMapping("/")
    public ResponseEntity<List<SeriesDTO>> getAllSeries() {
        List<SeriesDTO> seriesDTOList = seriesService.getAllSeries();
        return ResponseEntity.ok(seriesDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesDTO> getSeriesById(@PathVariable UUID id) {
        SeriesDTO seriesDTO = seriesService.getSeriesById(id);
        if (seriesDTO != null) {
            return ResponseEntity.ok(seriesDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesDTO> updateSeries(@PathVariable UUID id, @RequestBody SeriesDTO seriesDTO) {
        SeriesDTO updatedSeries = seriesService.updateSeries(id, seriesDTO);
        if (updatedSeries != null) {
            return ResponseEntity.ok(updatedSeries);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable UUID id) {
        seriesService.deleteSeries(id);
        return ResponseEntity.ok().build();
    }
}