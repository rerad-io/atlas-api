package com.example.medatlas.util;

import com.example.medatlas.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTOCreator {


    public static AnatomicalStructureDTO createAnatomicalStructureDTO() {
        AnatomicalStructureDTO structureDTO = new AnatomicalStructureDTO();
        structureDTO.setId(UUID.randomUUID());
        structureDTO.setName("Sample Name");
        return structureDTO;
    }

    public static List<AnatomicalStructureSubjectWithoutStructuresDTO> createAnatomicalStructureSubjectDTOList(int count) {
        List<AnatomicalStructureSubjectWithoutStructuresDTO> subjectDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            AnatomicalStructureSubjectWithoutStructuresDTO subjectDTO = createAnatomicalStructureSubjectWithoutStructuresDTO();
            subjectDTOList.add(subjectDTO);
        }
        return subjectDTOList;
    }

    public static InstanceDataDTO createInstanceDataDTO() {
        InstanceDataDTO instanceDataDTO = new InstanceDataDTO();
        instanceDataDTO.setId(UUID.randomUUID());
        return instanceDataDTO;
    }

    public static List<InstanceDataDTO> createInstanceDataDTOList(int count) {
        List<InstanceDataDTO> instanceDataDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            instanceDataDTOList.add(createInstanceDataDTO());
        }
        return instanceDataDTOList;
    }

    public static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SeriesDTO createSeriesDTO() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setId(UUID.randomUUID());
        seriesDTO.setNumber(1);
        seriesDTO.setName("Sample Name");
        return seriesDTO;
    }

    public static List<SeriesDTO> createSeriesDTOList(int count) {
        List<SeriesDTO> seriesDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            seriesDTOList.add(createSeriesDTO());
        }
        return seriesDTOList;
    }

    public static SeriesDTO getSeriesDTO() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setId(UUID.randomUUID());
        seriesDTO.setNumber(1);
        seriesDTO.setName("Example Name");
        return seriesDTO;
    }

    public static List<SeriesDTO> getSeriesDtoList() {
        return createSeriesDTOList(2);
    }

    public static StudyDTO createStudyDTO() {
        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setId(UUID.randomUUID());
        studyDTO.setName("Sample Name");
        return studyDTO;
    }

    public static List<StudyDTO> createStudyDTOList(int count) {
        List<StudyDTO> studyDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            studyDTOList.add(createStudyDTO());
        }
        return studyDTOList;
    }

    public static StudyDTO getStudyDTO() {
        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setId(UUID.randomUUID());
        studyDTO.setName("Study # 7 ");
        return studyDTO;
    }

    public static List<StudyDTO> getStudyDTOList() {
        return createStudyDTOList(2);
    }

    public static AnatomicalStructureSubjectDTO createAnatomicalStructureSubjectDTO() {
        AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
        subjectDTO.setId(UUID.randomUUID());
        subjectDTO.setName("Sample Name");
        subjectDTO.setColor("Sample Color");

        List<AnatomicalStructureWithoutSubjectDTO> children = createAnatomicalStructureWithoutSubjectDTOList();
        subjectDTO.setAnatomicalStructures(children);

        return subjectDTO;
    }

    private static List<AnatomicalStructureWithoutSubjectDTO> createAnatomicalStructureWithoutSubjectDTOList() {
        List<AnatomicalStructureWithoutSubjectDTO> structureDTOList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            AnatomicalStructureWithoutSubjectDTO structureDTO = new AnatomicalStructureWithoutSubjectDTO();
            structureDTO.setId(UUID.randomUUID());
            structureDTO.setName("AnatomicalStructure-" + i + " ");
            structureDTOList.add(structureDTO);
        }
        return structureDTOList;
    }

    public static AnatomicalStructureSubjectWithoutStructuresDTO createAnatomicalStructureSubjectWithoutStructuresDTO() {
        AnatomicalStructureSubjectWithoutStructuresDTO dto = new AnatomicalStructureSubjectWithoutStructuresDTO();
        dto.setId(UUID.randomUUID());
        dto.setName("Example Name");
        dto.setColor("Example Color");
        return dto;
    }

    public static List<SeriesDTO> getSeriesDTOList() {
        return createSeriesDTOList(2);
    }
}