package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.model.AnatomicalStructure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnatomicalStructureMapper {
    AnatomicalStructureMapper INSTANCE = Mappers.getMapper(AnatomicalStructureMapper.class);

    AnatomicalStructureDTO toDTO(AnatomicalStructure structure);

    AnatomicalStructure toEntity(AnatomicalStructureDTO structureDTO);
}