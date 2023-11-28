package com.example.medatlas.repository;

import com.example.medatlas.model.AnatomicalStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnatomicalStructureRepository extends JpaRepository<AnatomicalStructure, UUID> {
    @Query("SELECT a FROM AnatomicalStructure a WHERE (:name is null or lower(a.name) LIKE lower(concat('%', :name, '%'))) AND (:anatomicalStructureSubjectId is null or a.anatomicalStructureSubject.id = :anatomicalStructureSubjectId) " +
            "ORDER BY " +
            "CASE WHEN :orderBy = 'name' AND :orderByDirection = 'asc' THEN a.name END ASC, " +
            "CASE WHEN :orderBy = 'name' AND :orderByDirection = 'desc' THEN a.name END DESC")
    List<AnatomicalStructure> searchAnatomicalStructures(
            @Param("name") String name,
            @Param("anatomicalStructureSubjectId") UUID anatomicalStructureSubjectId,
            @Param("orderBy") String orderBy,
            @Param("orderByDirection") String orderByDirection);
}