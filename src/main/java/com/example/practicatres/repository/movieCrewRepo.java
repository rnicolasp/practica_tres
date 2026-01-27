package com.example.practicatres.repository;

import com.example.practicatres.models.entities.movie_crew;
import com.example.practicatres.models.entities.movie_crew_id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface movieCrewRepo extends JpaRepository<movie_crew, movie_crew_id> {
    @Query("SELECT mc FROM movie_crew mc WHERE mc.id.movie_id = :movieId")
    List<movie_crew> findByMovieId(@Param("movieId") Integer movieId);
}