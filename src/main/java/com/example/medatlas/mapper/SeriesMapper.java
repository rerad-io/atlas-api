package com.example.medatlas.mapper;

import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.model.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeriesMapper {
    //    SeriesMapper INSTANCE = Mappers.getMapper(SeriesMapper.class);
    @Mapping(source = "id", target = "id")
    SeriesDTO toDTO(Series series);

    @Mapping(source = "id", target = "id")
    Series toEntity(SeriesDTO seriesDTO);
}