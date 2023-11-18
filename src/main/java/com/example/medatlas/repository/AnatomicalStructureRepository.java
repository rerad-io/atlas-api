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
    @Query("SELECT a FROM AnatomicalStructure a WHERE lower(a.name) LIKE lower(concat('%', :name, '%'))")
    List<AnatomicalStructure> findByNameIgnoreCase(@Param("name") String name);


    AnatomicalStructure findByName(String name);
}