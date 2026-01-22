package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer gender_id;
    @Column(length = 20)
    String gender;

    public Integer getGender_id() {
        return gender_id;
    }

    public void setGender_id(Integer gender_id) {
        this.gender_id = gender_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
