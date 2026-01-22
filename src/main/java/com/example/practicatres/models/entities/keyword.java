package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
public class keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer keyword_id;
    @Column(length = 100)
    String keyword_name;

    public Integer getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(Integer keyword_id) {
        this.keyword_id = keyword_id;
    }

    public String getKeyword_name() {
        return keyword_name;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }
}
