package com.example.practicatres.services;

import com.example.practicatres.models.entities.language;
import com.example.practicatres.repository.languageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class languageService {
    @Autowired
    languageRepo languageRepository;

    public List<language> findAll() {
        return languageRepository.findAll();
    }

    public language findById(Integer id) {
        return languageRepository.findById(id).orElse(null);
    }

    public void save(language language) {
        languageRepository.save(language);
    }

    public void delete(Integer id) {
        languageRepository.deleteById(id);
    }
}