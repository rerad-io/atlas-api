package com.example.medatlas.controller;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.service.InstanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/InstanceData")
public class InstanceDataController {

    private final InstanceDataService instanceDataService;

    @Autowired
    public InstanceDataController(InstanceDataService instanceDataService) {
        this.instanceDataService = instanceDataService;
    }

    @PostMapping("/create")
    public ResponseEntity<InstanceDataDTO> createInstanceData(@RequestBody InstanceDataDTO instanceDataDTO) {
        InstanceDataDTO createdInstanceData = instanceDataService.createInstanceData(instanceDataDTO);
        return ResponseEntity.ok(createdInstanceData);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InstanceDataDTO>> getAllInstanceData() {
        List<InstanceDataDTO> instanceDataDTOList = instanceDataService.getAllInstanceData();
        return ResponseEntity.ok(instanceDataDTOList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InstanceDataDTO> getInstanceDataById(@PathVariable UUID id) {
        InstanceDataDTO instanceDataDTO = instanceDataService.getInstanceDataById(id);
        if (instanceDataDTO != null) {
            return ResponseEntity.ok(instanceDataDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InstanceDataDTO> updateInstanceData(@PathVariable UUID id, @RequestBody InstanceDataDTO instanceDataDTO) {
        InstanceDataDTO updatedInstanceData = instanceDataService.updateInstanceData(id, instanceDataDTO);
        if (updatedInstanceData != null) {
            return ResponseEntity.ok(updatedInstanceData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInstanceData(@PathVariable UUID id) {
        instanceDataService.deleteInstanceData(id);
        return ResponseEntity.ok().build();
    }
}