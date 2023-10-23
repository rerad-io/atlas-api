package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.model.AnatomicalStructure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureMapper {
    @Mapping(source = "id", target = "id")
    AnatomicalStructureDTO toDTO(AnatomicalStructure structure);

    @Mapping(source = "id", target = "id")
    AnatomicalStructure toEntity(AnatomicalStructureDTO structureDTO);

    List<AnatomicalStructureDTO> toDTOList(List<AnatomicalStructure> structureList);
}