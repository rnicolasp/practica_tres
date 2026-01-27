package com.example.practicatres.models.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

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
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    ) Set<keyword> keywords = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_languages",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    ) Set<language> languages = new HashSet<>();

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public Set<genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<genre> genres) {
        this.genres = genres;
    }

    public Set<keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<keyword> keywords) {
        this.keywords = keywords;
    }

    public Set<language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<language> languages) {
        this.languages = languages;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public void setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public BigInteger getRevenue() {
        return revenue;
    }

    public void setRevenue(BigInteger revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getMovie_status() {
        return movie_status;
    }

    public void setMovie_status(String movie_status) {
        this.movie_status = movie_status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public BigDecimal getVote_average() {
        return vote_average;
    }

    public void setVote_average(BigDecimal vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }
}