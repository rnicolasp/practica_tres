package com.example.practicatres.models.dto;

public class DirectorDTO {
    public String label;
    public String value;

    public DirectorDTO(String name) {
        this.label = name;
        this.value = name;
    }

    public String getLabel() { return label; }
    public String getValue() { return value; }
}
