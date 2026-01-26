package com.example.practicatres.services;

import com.example.practicatres.models.entities.country;
import com.example.practicatres.repository.countryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class countryService {
    @Autowired
    countryRepo countryRepository;

    public List<country> findAll() {
        return countryRepository.findAll();
    }

    public country findById(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    public void save(country country) {
        countryRepository.save(country);
    }

    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }
}