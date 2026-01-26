package com.example.practicatres.repository;

import com.example.practicatres.models.entities.country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface countryRepo extends JpaRepository<country, Integer> {

}