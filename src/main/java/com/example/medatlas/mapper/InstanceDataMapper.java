package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.InstanceData;
import com.example.medatlas.model.Series;
import com.example.medatlas.model.Study;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstanceDataMapper {
    @Mappings({
            @Mapping(target = "studyId", source = "study.id"),
            @Mapping(target = "studyName", source = "study.name"),
            @Mapping(target = "seriesId", source = "series.id"),
            @Mapping(target = "seriesName", source = "series.name"),
            @Mapping(target = "seriesNumber", source = "series.number"),
            @Mapping(target = "structureId", source = "structure.id"),
            @Mapping(target = "structureName", source = "structure.name"),
            @Mapping(target = "status", source = "status"),
    })
    InstanceDataDTO toDTO(InstanceData instanceData);

    InstanceData toEntity(InstanceDataDTO instanceDataDTO);

    Study toEntity(StudyDTO studyDTO);

    StudyDTO toDTO(Study study);

    Series toEntity(SeriesDTO seriesDTO);

    SeriesDTO toDTO(Series series);

    AnatomicalStructure toEntity(AnatomicalStructureDTO anatomicalStructureDTO);

    AnatomicalStructureDTO toDTO(AnatomicalStructure anatomicalStructure);

    List<InstanceDataDTO> toDTOList(List<InstanceData> instanceDataList);
}