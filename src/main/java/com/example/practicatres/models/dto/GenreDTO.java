package com.example.practicatres.models.dto;

public class GenreDTO {
    public Integer id;
    public String name;

    public GenreDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
}
