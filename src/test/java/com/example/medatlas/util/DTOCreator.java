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
        structureDTO.setSubjectDTO(createAnatomicalStructureSubjectDTO());
        structureDTO.setName("Sample Name");
        return structureDTO;
    }

    public static List<AnatomicalStructureDTO> createAnatomicalStructureDTOList(int count) {
        List<AnatomicalStructureDTO> anatomicalStructureDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            anatomicalStructureDTOList.add(createAnatomicalStructureDTO());
        }
        return anatomicalStructureDTOList;
    }

    public static AnatomicalStructureSubjectDTO createAnatomicalStructureSubjectDTO() {
        AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
        subjectDTO.setId(UUID.randomUUID());
        subjectDTO.setName("Sample Name");
        subjectDTO.setColor("Sample Color");
        return subjectDTO;
    }

    public static List<AnatomicalStructureDTO> getAnatomicalStructureDtoList() {
        return createAnatomicalStructureDTOList(2); // Пример списка из двух элементов
    }

    public static AnatomicalStructureSubjectDTO getSubject() {
        return createAnatomicalStructureSubjectDTO();
    }

    public static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AnatomicalStructureDTO getAnatomicalStructureDTO() {
        AnatomicalStructureDTO structureDTO = new AnatomicalStructureDTO();
        structureDTO.setId(UUID.randomUUID());

        AnatomicalStructureSubjectDTO subjectDTO = new AnatomicalStructureSubjectDTO();
        subjectDTO.setId(UUID.randomUUID());
        subjectDTO.setName("Sample Name");
        subjectDTO.setColor("Sample Color");

        structureDTO.setSubjectDTO(subjectDTO);
        structureDTO.setName("Example Structure");

        return structureDTO;
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

    public static InstanceDataDTO getInstanceDataDTO() {
        InstanceDataDTO instanceDataDTO = new InstanceDataDTO();
        instanceDataDTO.setId(UUID.randomUUID());
        instanceDataDTO.setName("Example Name");
        return instanceDataDTO;
    }

    public static List<InstanceDataDTO> getInstanceDataDtoList() {
        return createInstanceDataDTOList(2); // Пример списка из двух элементов
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
        for (int i = 0; i< count;
        i++){
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
    public static StudyDTO getStudyDTO() {
        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setId(UUID.randomUUID());
        studyDTO.setExternalId(UUID.randomUUID());
        studyDTO.setName("Example Study");
        studyDTO.setDescription("Sample description");
        studyDTO.setPreviewFrame("Sample preview frame");
        return studyDTO;
    }

    public static List<StudyDTO> createStudyDTOList(int count) {
        List<StudyDTO> studyDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            studyDTOList.add(getStudyDTO());
        }
        return studyDTOList;
    }

    public static List<StudyDTO> getStudyDTOList() {
        return createStudyDTOList(2); // Пример списка из двух элементов
    }
}