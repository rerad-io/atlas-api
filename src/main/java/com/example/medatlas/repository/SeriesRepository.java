package com.example.medatlas.repository;
import com.example.medatlas.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeriesRepository extends JpaRepository<Series, UUID> {
    List<Series> findByStudyId(UUID studyId);
}