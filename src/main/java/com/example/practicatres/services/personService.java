package com.example.practicatres.services;

import com.example.practicatres.models.entities.person;
import com.example.practicatres.repository.personRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class personService {
    @Autowired
    personRepo personRepository;

    public List<person> findAll() {
        return personRepository.findAll();
    }

    public person findById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    public void save(person person) {
        personRepository.save(person);
    }

    public void delete(Integer id) {
        personRepository.deleteById(id);
    }
}