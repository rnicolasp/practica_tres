package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer person_id;
    @Column(length = 500)
    String person_name;
}
