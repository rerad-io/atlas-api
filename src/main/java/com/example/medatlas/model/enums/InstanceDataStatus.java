package com.example.medatlas.model.enums;

import lombok.Getter;

@Getter
public enum InstanceDataStatus {
    UNVERIFIED(0),
    VERIFIED(1);
    private final int code;

    InstanceDataStatus(int code) {
        this.code = code;
    }
}