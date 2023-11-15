package com.example.medatlas.service;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;

import java.util.List;
import java.util.UUID;

public interface SeriesService {
    SeriesDTO createSeries(SeriesDTO seriesDTO);

    SeriesDTO getSeriesById(UUID id);

    List<SeriesDTOWithoutStudy> getAllSeries();

    SeriesDTO updateSeries(UUID id, SeriesDTO seriesDTO);

    void deleteSeries(UUID id);

    List<InstanceDataDTO> getInstanceDataForSeries(UUID id);
}