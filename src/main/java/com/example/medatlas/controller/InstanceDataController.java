package com.example.medatlas.controller;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.service.InstanceDataService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/InstanceData")
@Api(value = "InstanceData API", tags = {"API endpoints for the InstanceData Controller"})
public class InstanceDataController {

    private final InstanceDataService instanceDataService;

    @Autowired
    public InstanceDataController(InstanceDataService instanceDataService) {
        this.instanceDataService = instanceDataService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create an anatomical instance data")
    public ResponseEntity<InstanceDataDTO> createInstanceData(@RequestBody InstanceDataDTO instanceDataDTO) {
        InstanceDataDTO createdInstanceData = instanceDataService.createInstanceData(instanceDataDTO);
        return ResponseEntity.ok(createdInstanceData);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all anatomical instance data")
    public ResponseEntity<List<InstanceDataDTO>> getAllInstanceData() {
        List<InstanceDataDTO> instanceDataDTOList = instanceDataService.getAllInstanceData();
        return ResponseEntity.ok(instanceDataDTOList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get anatomical instance data by ID")
    public ResponseEntity<InstanceDataDTO> getInstanceDataById(@PathVariable UUID id) {
        InstanceDataDTO instanceDataDTO = instanceDataService.getInstanceDataById(id);
        if (instanceDataDTO != null) {
            return ResponseEntity.ok(instanceDataDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an anatomical instance data")
    public ResponseEntity<InstanceDataDTO> updateInstanceData(@PathVariable UUID id, @RequestBody InstanceDataDTO instanceDataDTO) {
        InstanceDataDTO updatedInstanceData = instanceDataService.updateInstanceData(id, instanceDataDTO);
        if (updatedInstanceData != null) {
            return ResponseEntity.ok(updatedInstanceData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an anatomical instance data")
    public ResponseEntity<Void> deleteInstanceData(@PathVariable UUID id) {
        instanceDataService.deleteInstanceData(id);
        return ResponseEntity.noContent().build();
    }
}