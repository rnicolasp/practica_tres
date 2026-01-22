package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class language_role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer role_id;
    @Column(length = 20)
    String language_role;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getLanguage_role() {
        return language_role;
    }

    public void setLanguage_role(String language_role) {
        this.language_role = language_role;
    }
}
