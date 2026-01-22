package com.example.practicatres.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class movie {

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    ) Set<genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_keywords",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "keyboard_id")
    ) Set<genre> keywords = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_languages",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    ) Set<genre> languages = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer movie_id;
    @Column(length = 1000)
    String title;
    Integer budget;
    @Column(length = 1000)
    String homepage;
    @Column(length = 1000)
    String overview;
    @Column(precision = 12, scale = 6)
    BigDecimal popularity;
    Date release_date;
    BigInteger revenue;
    Integer runtime;
    @Column(length = 50)
    String movie_status;
    @Column(length = 1000)
    String tagline;
    @Column(precision = 4, scale = 2)
    BigDecimal vote_average;
    Integer vote_count;

}