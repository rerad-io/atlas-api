package com.example.medatlas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnatomicalStructureSubjectExceptionHandler {

    @ExceptionHandler(DuplicateNameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<String> handleDuplicateName(DuplicateNameException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate name error: " + ex.getMessage());
    }

    @ExceptionHandler(DuplicateColorException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<String> handleDuplicateColor(DuplicateColorException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate color error: " + ex.getMessage());
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<String> handleSubjectNotFound(SubjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found: " + ex.getMessage());
    }

    @ExceptionHandler(NestedStructuresException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<String> handleNestedStructures(NestedStructuresException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Nested structures error: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + ex.getMessage());
    }
}