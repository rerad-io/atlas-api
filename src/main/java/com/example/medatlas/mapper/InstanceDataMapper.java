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

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstanceDataMapper {
    @Mapping(target = "studyDTO", source = "study")
    @Mapping(target = "seriesDTO", source = "series")
    @Mapping(target = "anatomicalStructureDTO", source = "structure")
    InstanceDataDTO toDTO(InstanceData instanceData);

    @Mapping(target = "study", source = "studyDTO")
    @Mapping(target = "series", source = "seriesDTO")
    @Mapping(target = "structure", source = "anatomicalStructureDTO")
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
}