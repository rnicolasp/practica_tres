package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer department_id;
    @Column(length = 200)
    String department_name;

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
