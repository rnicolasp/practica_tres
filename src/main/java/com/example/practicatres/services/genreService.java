package com.example.practicatres.services;

import com.example.practicatres.models.entities.genre;
import com.example.practicatres.repository.genreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class genreService {
    @Autowired
    genreRepo genreRepository;

    public List<genre> findAll() {
        return genreRepository.findAll();
    }

    public genre findById(Integer id) {
        return genreRepository.findById(id).orElse(null);
    }

    public void save(genre genre) {
        genreRepository.save(genre);
    }

    public void delete(Integer id) {
        genreRepository.deleteById(id);
    }
}