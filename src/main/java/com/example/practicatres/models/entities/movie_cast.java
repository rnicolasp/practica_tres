package com.example.practicatres.models.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "movie_cast")
public class movie_cast {

    @EmbeddedId
    movie_cast_id id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    movie movie_id;

    @ManyToOne
    @MapsId("person_id")
    @JoinColumn(name = "person_id")
    person person_id;

    @ManyToOne
    @MapsId("gender_id")
    @JoinColumn(name = "gender_id")
    gender gender_id;

    @Column(length = 400)
    private String character_name;
    private Integer cast_order;
}