package com.example.medatlas.service.impl;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.mapper.SeriesMapper;
import com.example.medatlas.model.Series;
import com.example.medatlas.repository.SeriesRepository;
import com.example.medatlas.service.SeriesService;
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

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository, SeriesMapper seriesMapper) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
    }

    @Override
    public SeriesDTO createSeries(SeriesDTO seriesDTO) {
        // Implement the creation of a new Series and return the DTO
        // Реализуем создание новой серии и возвращаем DTO
        Series series = seriesMapper.toEntity(seriesDTO);
        series = seriesRepository.save(series);
        return seriesMapper.toDTO(series);
    }

    @Override
    public SeriesDTO getSeriesById(UUID id) {
        // Implement the retrieval of a Series by ID and return the DTO
        // Реализуем получение серии по идентификатору и возвращаем DTO
        Series series = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        return seriesMapper.toDTO(series);
    }

    @Override
    public List<SeriesDTO> getAllSeries() {
        // Implement the retrieval of all Series and return a list of DTOs
        // Реализуем получение всех серий и возвращаем список DTO
        List<Series> seriesList = seriesRepository.findAll();
        return seriesList.stream().map(seriesMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SeriesDTO updateSeries(UUID id, SeriesDTO seriesDTO) {
        // Implement the update of a Series and return the updated DTO
        // Реализуем обновление серии и возвращаем обновленный DTO
        Series existingSeries = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        Series updatedSeries = seriesMapper.toEntity(seriesDTO);
        updatedSeries.setId(existingSeries.getId());
        updatedSeries = seriesRepository.save(updatedSeries);
        return seriesMapper.toDTO(updatedSeries);
    }

    @Override
    public void deleteSeries(UUID id) {
        // Implement the deletion of a Series by ID
        Series series = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        seriesRepository.delete(series);
    }
}