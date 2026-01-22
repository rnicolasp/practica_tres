package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer country_id;
    @Column(length = 10)
    String country_iso_code;
    @Column(length = 200)
    String country_name;

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public String getCountry_iso_code() {
        return country_iso_code;
    }

    public void setCountry_iso_code(String country_iso_code) {
        this.country_iso_code = country_iso_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
