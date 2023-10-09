package com.example.medatlas.controller;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.mapper.SeriesMapper;
import com.example.medatlas.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    private final SeriesService seriesService;
    private final SeriesMapper seriesMapper;

    @Autowired
    public SeriesController(SeriesService seriesService, SeriesMapper seriesMapper) {
        this.seriesService = seriesService;
        this.seriesMapper = seriesMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<SeriesDTO> createSeries(@RequestBody SeriesDTO seriesDTO) {
        SeriesDTO createdSeries = seriesService.createSeries(seriesDTO);
        return ResponseEntity.ok(createdSeries);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SeriesDTO> getSeriesById(@PathVariable UUID id) {
        SeriesDTO seriesDTO = seriesService.getSeriesById(id);
        if (seriesDTO != null) {
            return ResponseEntity.ok(seriesDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SeriesDTO> updateSeries(@PathVariable UUID id, @RequestBody SeriesDTO seriesDTO) {
        SeriesDTO updatedSeries = seriesService.updateSeries(id, seriesDTO);
        if (updatedSeries != null) {
            return ResponseEntity.ok(updatedSeries);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}