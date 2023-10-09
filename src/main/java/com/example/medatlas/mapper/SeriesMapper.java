package com.example.medatlas.mapper;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.model.Series;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SeriesMapper {
    SeriesMapper INSTANCE = Mappers.getMapper(SeriesMapper.class);

    SeriesDTO toDTO(Series series);

    Series toEntity(SeriesDTO seriesDTO);
}