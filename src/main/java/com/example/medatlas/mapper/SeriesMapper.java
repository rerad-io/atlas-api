package com.example.medatlas.mapper;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.model.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeriesMapper {
    @Mapping(source = "id", target = "id")
    SeriesDTO toDTO(Series series);

    @Mapping(source = "id", target = "id")
    Series toEntity(SeriesDTO seriesDTO);

    SeriesDTOWithoutStudy toEntity(SeriesDTOWithoutStudy seriesDTOWithoutStudy);

    SeriesDTOWithoutStudy toDTOWithoutStudy(Series series);
}