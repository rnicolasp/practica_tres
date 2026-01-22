package com.example.practicatres.models.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class movie_cast_id {
    Integer movie_id;
    Integer person_id;
    Integer gender_id;
}