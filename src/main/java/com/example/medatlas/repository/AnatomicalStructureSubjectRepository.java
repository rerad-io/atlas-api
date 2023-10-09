package com.example.medatlas.repository;
import com.example.medatlas.model.AnatomicalStructureSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnatomicalStructureSubjectRepository extends JpaRepository<AnatomicalStructureSubject, UUID> {
}