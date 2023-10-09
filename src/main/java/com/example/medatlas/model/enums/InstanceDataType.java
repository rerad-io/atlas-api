package com.example.medatlas.model.enums;
public enum InstanceDataType {
    Point(0),
    Area(1);

    private final int value;

    InstanceDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static InstanceDataType fromValue(int value) {
        for (InstanceDataType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid InstanceDataType value: " + value);
    }
}