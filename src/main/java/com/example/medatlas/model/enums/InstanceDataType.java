package com.example.medatlas.model.enums;

import lombok.Getter;

@Getter
public enum InstanceDataType {
    POINT(0),
    AREA(1);

    private final int value;

    InstanceDataType(int value) {
        this.value = value;
    }
}