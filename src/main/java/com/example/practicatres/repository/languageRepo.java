package com.example.practicatres.repository;

import com.example.practicatres.models.entities.language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface languageRepo extends JpaRepository<language, Integer> {

}