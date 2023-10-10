package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.model.AnatomicalStructure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureMapper {
    AnatomicalStructureMapper INSTANCE = Mappers.getMapper(AnatomicalStructureMapper.class);

    AnatomicalStructureDTO toDTO(AnatomicalStructure structure);

    AnatomicalStructure toEntity(AnatomicalStructureDTO structureDTO);

    List<AnatomicalStructureDTO> toDTOList(List<AnatomicalStructure> structureList);
}