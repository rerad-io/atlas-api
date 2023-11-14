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

    InstanceDataDTO toDTO(InstanceData instanceData);

    InstanceData toEntity(InstanceDataDTO instanceDataDTO);
//    @Mapping(source = "id", target = "id")
    Study toEntity(StudyDTO studyDTO);
//    @Mapping(source = "id", target = "id")
    StudyDTO toDTO(Study study);
//    @Mapping(source = "id", target = "id")
    Series toEntity(SeriesDTO seriesDTO);
//    @Mapping(source = "id", target = "id")
    SeriesDTO toDTO(Series series);

//    @Mapping(source = "id", target = "id")
    AnatomicalStructure toEntity(AnatomicalStructureDTO anatomicalStructureDTO);

//    @Mapping(source = "id", target = "id")
    AnatomicalStructure toDTO(AnatomicalStructure anatomicalStructure);

    List<InstanceDataDTO> toDTOList(List<InstanceData> instanceDataList);

    List<InstanceData> toEntityList(List<InstanceDataDTO> instanceDataDTOList);
}