package com.example.medatlas.service;

import com.example.medatlas.dto.SeriesDTO;

import java.util.List;
import java.util.UUID;

public interface SeriesService {
    SeriesDTO createSeries(SeriesDTO seriesDTO);

    SeriesDTO getSeriesById(UUID id);

    List<SeriesDTO> getAllSeries();

    SeriesDTO updateSeries(UUID id, SeriesDTO seriesDTO);

    void deleteSeries(UUID id);
}