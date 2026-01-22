package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer language_id;
    @Column(length = 10)
    String language_code;
    @Column(length = 500)
    String language_name;

    public Integer getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Integer language_id) {
        this.language_id = language_id;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }
}
