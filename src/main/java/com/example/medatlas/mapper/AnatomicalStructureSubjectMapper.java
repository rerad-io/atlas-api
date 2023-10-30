package com.example.medatlas.mapper;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.mapstruct.*;

import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AnatomicalStructureSubjectMapper {

    @Mapping(target = "anatomicalStructures", ignore = true)
    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject, @Context Map<UUID, AnatomicalStructureSubjectDTO> context);

    AnatomicalStructureSubject toEntity(AnatomicalStructureSubjectDTO subjectDTO);

    @IterableMapping(qualifiedByName = "toDTOWithCheck")
    Iterable<AnatomicalStructureSubjectDTO> toDTOList(Iterable<AnatomicalStructureSubject> subjectList, @Context Map<UUID, AnatomicalStructureSubjectDTO> context);

    @Named("toDTOWithCheck")
    default AnatomicalStructureSubjectDTO toDTOWithCheck(AnatomicalStructureSubject subject, @Context Map<UUID, AnatomicalStructureSubjectDTO> context) {
        // Проверка, был ли объект уже промаплен
        if (context.containsKey(subject.getId())) {
            return context.get(subject.getId());
        }

        // Создание DTO и добавление его в контекст
        AnatomicalStructureSubjectDTO dto = toDTO(subject, context);
        context.put(subject.getId(), dto);

        return dto;
    }

    AnatomicalStructureSubjectDTO toDTO(AnatomicalStructureSubject subject);
}