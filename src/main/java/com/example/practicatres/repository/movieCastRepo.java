package com.example.practicatres.repository;

import com.example.practicatres.models.entities.movie_cast;
import com.example.practicatres.models.entities.movie_cast_id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface movieCastRepo extends JpaRepository<movie_cast, movie_cast_id> {
    @Query("SELECT mc FROM movie_cast mc WHERE mc.id.movie_id = :movieId")
    List<movie_cast> findByMovieId(@Param("movieId") Integer movieId);
}