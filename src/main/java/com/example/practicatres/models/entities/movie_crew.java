package com.example.practicatres.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_crew")
public class movie_crew {

    @EmbeddedId
    movie_crew_id id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private person person;

    @Column(length = 200)
    private String job;

    @Column(length = 200)
    private String department;

    public movie getMovie() { return movie; }
    public person getPerson() { return person; }
    public String getJob() { return job; }

    public void setMovie(movie movie) {
        this.movie = movie;
    }

    public void setPerson(person person) {
        this.person = person;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}