package com.example.medatlas.repository;

import com.example.medatlas.model.AnatomicalStructureSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnatomicalStructureSubjectRepository extends JpaRepository<AnatomicalStructureSubject, UUID> {
    @Query("SELECT a FROM AnatomicalStructureSubject a WHERE lower(a.name) LIKE lower(concat('%', :name, '%'))")
    List<AnatomicalStructureSubject> findByNameIgnoreCase(@Param("name") String name);
}