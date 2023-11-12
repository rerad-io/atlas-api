package com.example.medatlas.repository;
import com.example.medatlas.model.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudyRepository extends JpaRepository<Study, UUID> {
    interface StudySummary {
        UUID getId();
        String getExternalId();
        String getName();
        String getDescription();
        String getPreviewFrame();
    }

    List<StudySummary> findAllProjectedBy();
}