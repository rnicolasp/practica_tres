package com.example.practicatres.repository;

import com.example.practicatres.models.entities.gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface genderRepo extends JpaRepository<gender, Integer> {

}