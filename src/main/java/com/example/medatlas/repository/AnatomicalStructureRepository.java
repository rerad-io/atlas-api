package com.example.medatlas.repository;
import com.example.medatlas.model.AnatomicalStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnatomicalStructureRepository extends JpaRepository<AnatomicalStructure, UUID> {
    AnatomicalStructure findByName(String structureDTO);
}