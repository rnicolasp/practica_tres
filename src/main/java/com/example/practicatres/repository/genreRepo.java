package com.example.practicatres.repository;

import com.example.practicatres.models.entities.genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface genreRepo extends JpaRepository<genre, Integer> {

}
