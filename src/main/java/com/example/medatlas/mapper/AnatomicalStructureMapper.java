package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.model.AnatomicalStructure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureMapper {
    //    AnatomicalStructureMapper INSTANCE = Mappers.getMapper(AnatomicalStructureMapper.class);
    @Mapping(source = "id", target = "id")
    AnatomicalStructureDTO toDTO(AnatomicalStructure structure);

    @Mapping(source = "id", target = "id")
    AnatomicalStructure toEntity(AnatomicalStructureDTO structureDTO);

    @Mapping(source = "id", target = "id")
    List<AnatomicalStructureDTO> toDTOList(List<AnatomicalStructure> structureList);
}