package com.example.practicatres.repository;

import com.example.practicatres.models.entities.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface movieRepo extends JpaRepository<movie, Integer> {
    List<movie> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT m FROM movie m JOIN m.genres g WHERE g.genre_name LIKE %:genreName%")
    List<movie> findByGenreName(@Param("genreName") String genreName);

    @Query("SELECT DISTINCT m FROM movie_cast mc " +
            "JOIN mc.movie m " +
            "JOIN mc.person p " +
            "WHERE p.person_name LIKE %:name% OR mc.character_name LIKE %:name%")
    List<movie> findByActorOrCharacterName(@Param("name") String name);

    @Query("SELECT DISTINCT m FROM movie_crew mcr " +
            "JOIN mcr.movie m " +
            "JOIN mcr.person p " +
            "WHERE p.person_name LIKE %:name% AND mcr.job = 'Director'")
    List<movie> findByDirectorName(@Param("name") String name);
}
