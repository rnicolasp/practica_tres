package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer company_id;
    @Column(length = 200)
    String company_name;

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
