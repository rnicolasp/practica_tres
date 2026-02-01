package com.example.practicatres.services;

import com.example.practicatres.models.entities.keyword;
import com.example.practicatres.repository.keywordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class keywordService {
    @Autowired
    keywordRepo keywordRepo;

    public List<keyword> findAll() {
        return keywordRepo.findAll();
    }

    public keyword findById(Integer id) {
        return keywordRepo.findById(id).orElse(null);
    }

    public void save(keyword keyword) {
        keywordRepo.save(keyword);
    }

    public void delete(Integer id) {
        keywordRepo.deleteById(id);
    }

    public Page<keyword> findPaginated(int page, int size, String query) {
        Pageable pageable = PageRequest.of(page, size);
        if (query == null || query.trim().isEmpty()) {
            return keywordRepo.findAll(pageable);
        }
        return keywordRepo.findByKeywordNameContainingIgnoreCase(query, pageable);
    }
}