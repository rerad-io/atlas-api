package com.example.medatlas.util;

import com.example.medatlas.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTOCreator {

    public static AnatomicalStructureWithSubjectDTO createAnatomicalStructureDTO() {
        AnatomicalStructureWithSubjectDTO structureDTO = new AnatomicalStructureWithSubjectDTO();
        structureDTO.setId(UUID.randomUUID());
        structureDTO.setName("Sample Name");
        return structureDTO;
    }

    public static AnatomicalStructureSubjectWithChildrenDTO createAnatomicalStructureSubjectDTO() {
        AnatomicalStructureSubjectWithChildrenDTO subjectDTO = new AnatomicalStructureSubjectWithChildrenDTO();
        subjectDTO.setId(UUID.randomUUID());
        subjectDTO.setName("Sample Name");
        return subjectDTO;
    }

    public static List<AnatomicalStructureSubjectDTO> createAnatomicalStructureSubjectDTOList(int count) {
        List<AnatomicalStructureSubjectDTO> subjectDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            AnatomicalStructureSubjectWithChildrenDTO withChildrenDTO = createAnatomicalStructureSubjectWithChildrenDTO();
            AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
            subjectDTO.setId(withChildrenDTO.getId());
            subjectDTO.setName(withChildrenDTO.getName());
            subjectDTO.setColor(withChildrenDTO.getColor());
            subjectDTOList.add(subjectDTO);
        }
        return subjectDTOList;
    }

    public static InstanceDataDTO createInstanceDataDTO() {
        InstanceDataDTO instanceDataDTO = new InstanceDataDTO();
        instanceDataDTO.setId(UUID.randomUUID());
        instanceDataDTO.setName("Sample Name");
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
        return createSeriesDTOList(2); // Пример списка из двух элементов
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
        return createStudyDTOList(2); // Пример списка из двух элементов
    }

    public static AnatomicalStructureSubjectWithChildrenDTO createAnatomicalStructureSubjectWithChildrenDTO() {
        AnatomicalStructureSubjectWithChildrenDTO subjectDTO = new AnatomicalStructureSubjectWithChildrenDTO();
        subjectDTO.setId(UUID.randomUUID());
        subjectDTO.setName("Sample Name");
        subjectDTO.setColor("Sample Color");

        List<AnatomicalStructureDTO> children = createAnatomicalStructureDTOList(); // Пример списка из двух элементов
        subjectDTO.setAnatomicalStructures(children);

        return subjectDTO;
    }

    private static List<AnatomicalStructureDTO> createAnatomicalStructureDTOList() {
        List<AnatomicalStructureDTO> structureDTOList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            AnatomicalStructureDTO structureDTO = new AnatomicalStructureDTO();
            structureDTO.setId(UUID.randomUUID());
            structureDTO.setName("AnatomicalStructure-" + i+ " ");
            structureDTOList.add(structureDTO);
        }
        return structureDTOList;
    }
}