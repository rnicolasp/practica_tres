package com.example.practicatres.models.dto;

public class PersonDTO {
    public Integer id;
    public String name;

    public PersonDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
}
