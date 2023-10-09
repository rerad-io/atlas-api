package com.example.medatlas.mapper;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.model.InstanceData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstanceDataMapper {
    InstanceDataMapper INSTANCE = Mappers.getMapper(InstanceDataMapper.class);

    @Mapping(target = "studyDTO", source = "study") // Assuming you have a method to map Study
    @Mapping(target = "seriesDTO", source = "series") // Assuming you have a method to map Series
    @Mapping(target = "anatomicalStructureDTO", source = "structure") // Assuming you have a method to map AnatomicalStructure
    InstanceDataDTO toDTO(InstanceData instanceData);

    @Mapping(target = "study", source = "studyDTO") // Assuming you have a method to map StudyDTO
    @Mapping(target = "series", source = "seriesDTO") // Assuming you have a method to map SeriesDTO
    @Mapping(target = "structure", source = "anatomicalStructureDTO") // Assuming you have a method to map AnatomicalStructureDTO
    InstanceData toEntity(InstanceDataDTO instanceDataDTO);
}