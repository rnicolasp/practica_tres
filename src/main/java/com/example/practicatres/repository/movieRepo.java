package com.example.practicatres.repository;

import com.example.practicatres.models.entities.movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface movieRepo extends JpaRepository<movie, Integer> {
        List<movie> findByTitleContainingIgnoreCase(String title);
        Page<movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

        @Query("SELECT m FROM movie m JOIN m.genres g WHERE LOWER(g.genre_name) LIKE LOWER(CONCAT('%', :genreName, '%'))")
        List<movie> findByGenreName(@Param("genreName") String genreName);

        @Query("SELECT m FROM movie m JOIN m.genres g WHERE LOWER(g.genre_name) LIKE LOWER(CONCAT('%', :genreName, '%'))")
        Page<movie> findByGenreName(@Param("genreName") String genreName, Pageable pageable);

    @Query("SELECT DISTINCT m FROM movie_cast mc " +
            "JOIN mc.movie m " +
            "JOIN mc.person p " +
            "WHERE LOWER(p.person_name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(mc.character_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<movie> findByActorOrCharacterName(@Param("name") String name);

    @Query("SELECT DISTINCT m FROM movie_cast mc " +
            "JOIN mc.movie m " +
            "JOIN mc.person p " +
            "WHERE LOWER(p.person_name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(mc.character_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<movie> findByActorOrCharacterName(@Param("name") String name, Pageable pageable);

    @Query("SELECT DISTINCT m FROM movie_crew mcr " +
            "JOIN mcr.movie m " +
            "JOIN mcr.person p " +
            "WHERE LOWER(p.person_name) LIKE LOWER(CONCAT('%', :name, '%')) AND mcr.job = 'Director'")
    List<movie> findByDirectorName(@Param("name") String name);

    @Query("SELECT DISTINCT m FROM movie_crew mcr " +
            "JOIN mcr.movie m " +
            "JOIN mcr.person p " +
            "WHERE LOWER(p.person_name) LIKE LOWER(CONCAT('%', :name, '%')) AND mcr.job = 'Director'")
    Page<movie> findByDirectorName(@Param("name") String name, Pageable pageable);
}
