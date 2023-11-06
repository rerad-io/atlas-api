package com.example.medatlas.exception;

public class DuplicateColorException extends RuntimeException {
    public DuplicateColorException(String message) {
        super(message);
    }
}