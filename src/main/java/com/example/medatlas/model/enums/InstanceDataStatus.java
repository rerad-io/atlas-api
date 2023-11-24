package com.example.medatlas.model.enums;

import lombok.Getter;

@Getter
public enum InstanceDataStatus {
    UNVERIFIED(0, "unverified"),
    VERIFIED(1, "verified");
    private final int code;
    private final String description;

    InstanceDataStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}