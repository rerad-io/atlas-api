package com.example.medatlas.controller;


import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Study")
public class StudyController {

    private final StudyService studyService;

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudyDTO> createStudy(@RequestBody StudyDTO studyDTO) {
        StudyDTO createdStudy = studyService.createStudy(studyDTO);
        return ResponseEntity.ok(createdStudy);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudyDTO>> getAllStudies() {
        List<StudyDTO> studyDTOList = studyService.getAllStudies();
        return ResponseEntity.ok(studyDTOList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudyDTO> getStudyById(@PathVariable UUID id) {
        StudyDTO studyDTO = studyService.getStudyById(id);
        if (studyDTO != null) {
            return ResponseEntity.ok(studyDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudyDTO> updateStudy(@PathVariable UUID id, @RequestBody StudyDTO studyDTO) {
        StudyDTO updatedStudy = studyService.updateStudy(id, studyDTO);
        if (updatedStudy != null) {
            return ResponseEntity.ok(updatedStudy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable UUID id) {
        studyService.deleteStudy(id);
        return ResponseEntity.ok().build();
    }
}