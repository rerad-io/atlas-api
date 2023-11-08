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
            @Mapping(target = "study", source = "studyName"),
            @Mapping(target = "series", source = "seriesName"),
            @Mapping(target = "structure", source = "structureName")
    })
    InstanceDataDTO toDTO(InstanceData instanceData);

    @Mappings({
            @Mapping(target = "studyName", source = "study"),
            @Mapping(target = "seriesName", source = "series"),
            @Mapping(target = "structureName", source = "structure")
    })
    InstanceData toEntity(InstanceDataDTO instanceDataDTO);

    Study toEntity(StudyDTO studyDTO);

    StudyDTO toDTO(Study study);

    Series toEntity(SeriesDTO seriesDTO);

    SeriesDTO toDTO(Series series);

    @Mapping(source = "id", target = "id")
    AnatomicalStructure toEntity(AnatomicalStructureDTO anatomicalStructureDTO);

    @Mapping(source = "id", target = "id")
    AnatomicalStructureDTO toDTO(AnatomicalStructure anatomicalStructure);

    List<InstanceDataDTO> toDTOList(List<InstanceData> instanceDataList);

    Study studyToEntity(String studyDTO);

    Series seriesToEntity(String seriesDTO);

    AnatomicalStructure structureToEntity(String structureDTO);
}