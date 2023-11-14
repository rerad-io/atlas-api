package com.example.medatlas.service.impl;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.mapper.SeriesMapper;
import com.example.medatlas.model.Series;
import com.example.medatlas.repository.SeriesRepository;
import com.example.medatlas.service.SeriesService;
import com.example.medatlas.service.StudyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    private final StudyService studyService;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository, SeriesMapper seriesMapper, StudyService studyService) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
        this.studyService = studyService;
    }

    @Override
    public SeriesDTO createSeries(SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());
        if (parentStudy == null) {
            throw new EntityNotFoundException("Study not found with id: " + seriesDTO.getStudyId());
        }

        Series series = seriesMapper.toEntity(seriesDTO);
        series = seriesRepository.save(series);
        return seriesMapper.toDTO(series);
    }

    @Override
    public SeriesDTO getSeriesById(UUID id) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        return seriesMapper.toDTO(series);
    }

    @Override
    public List<SeriesDTOWithoutStudy> getAllSeries() {
        List<Series> seriesList = seriesRepository.findAll();
        return seriesList.stream().map(seriesMapper::toDTOWithoutStudy).collect(Collectors.toList());
    }

    @Override
    public SeriesDTO updateSeries(UUID id, SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());
        if (parentStudy == null) {
            throw new EntityNotFoundException("Study not found with id: " + seriesDTO.getStudyId());
        }

        Series existingSeries = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        Series updatedSeries = seriesMapper.toEntity(seriesDTO);
        updatedSeries.setId(existingSeries.getId());
        updatedSeries = seriesRepository.save(updatedSeries);
        return seriesMapper.toDTO(updatedSeries);
    }

    @Override
    public void deleteSeries(UUID id) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        seriesRepository.delete(series);
    }
}