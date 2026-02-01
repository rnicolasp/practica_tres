package com.example.practicatres.repository;

import com.example.practicatres.models.entities.movie_cast;
import com.example.practicatres.models.entities.movie_cast_id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface movieCastRepo extends JpaRepository<movie_cast, movie_cast_id> {
    @Query("SELECT mc FROM movie_cast mc WHERE mc.id.movie_id = :movieId")
    List<movie_cast> findByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT mc FROM movie_cast mc WHERE mc.id.movie_id = :movieId")
    Page<movie_cast> findByMovieIdPaginated(@Param("movieId") Integer movieId, Pageable pageable);

    @Query("SELECT mc FROM movie_cast mc JOIN mc.person p " +
            "WHERE LOWER(p.person_name) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(mc.character_name) LIKE LOWER(CONCAT('%', :q, '%'))")
    Page<movie_cast> searchByPersonOrCharacter(@Param("q") String q, Pageable pageable);
}