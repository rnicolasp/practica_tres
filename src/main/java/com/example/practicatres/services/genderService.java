package com.example.practicatres.services;

import com.example.practicatres.models.entities.gender;
import com.example.practicatres.repository.genderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class genderService {
    @Autowired
    genderRepo genderRepository;

    public List<gender> findAll() {
        return genderRepository.findAll();
    }

    public gender findById(Integer id) {
        return genderRepository.findById(id).orElse(null);
    }

    public void save(gender gender) {
        genderRepository.save(gender);
    }

    public void delete(Integer id) {
        genderRepository.deleteById(id);
    }
}