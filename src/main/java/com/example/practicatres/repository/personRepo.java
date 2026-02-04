package com.example.practicatres.repository;

import com.example.practicatres.models.entities.person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface personRepo extends JpaRepository<person, Integer> {
    @Query("SELECT p FROM person p WHERE LOWER(p.person_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<person> findByPersonNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT p FROM person p WHERE LOWER(p.person_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<person> findByPersonNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("SELECT DISTINCT p FROM movie_crew mc " +
            "JOIN mc.person p " +
            "WHERE mc.job = 'Director' AND LOWER(p.person_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<person> findDirectorsByNameContaining(@Param("name") String name);
}
