package com.example.practicatres.repository;

import com.example.practicatres.models.entities.person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface personRepo extends JpaRepository<person, Integer> {
}
