package com.example.practicatres.services;

import com.example.practicatres.models.entities.person;
import com.example.practicatres.repository.personRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    public List<person> findByNameContaining(String name) {
        return personRepository.findByPersonNameContainingIgnoreCase(name);
    }

    public Page<person> findPaginated(int page, int size, String query) {
        Pageable pageable = PageRequest.of(page, size);
        if (query == null || query.trim().isEmpty()) {
            return personRepository.findAll(pageable);
        }
        return personRepository.findByPersonNameContainingIgnoreCase(query, pageable);
    }
}