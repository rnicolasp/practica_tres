package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer person_id;
    @Column(length = 500)
    String person_name;

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }
}
