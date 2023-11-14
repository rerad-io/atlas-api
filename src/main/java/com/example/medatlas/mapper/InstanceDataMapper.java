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

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstanceDataMapper {

    InstanceDataDTO toDTO(InstanceData instanceData);

    InstanceData toEntity(InstanceDataDTO instanceDataDTO);

    Study toEntity(StudyDTO studyDTO);

    StudyDTO toDTO(Study study);

    Series toEntity(SeriesDTO seriesDTO);

    SeriesDTO toDTO(Series series);

    AnatomicalStructure toEntity(AnatomicalStructureDTO anatomicalStructureDTO);

    AnatomicalStructure toDTO(AnatomicalStructure anatomicalStructure);

    List<InstanceDataDTO> toDTOList(List<InstanceData> instanceDataList);

    List<InstanceData> toEntityList(List<InstanceDataDTO> instanceDataDTOList);
}