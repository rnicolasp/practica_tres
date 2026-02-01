package com.example.practicatres.services;

import com.example.practicatres.models.entities.movie_cast;
import com.example.practicatres.models.entities.movie_cast_id;
import com.example.practicatres.repository.movieCastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class movieCastService {
    @Autowired
    movieCastRepo movieCastRepository;

    public List<movie_cast> findAll() {
        return movieCastRepository.findAll();
    }

    public Page<movie_cast> searchPaginated(String q, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (q == null || q.trim().isEmpty()) {
            return movieCastRepository.findAll(pageable);
        }
        return movieCastRepository.searchByPersonOrCharacter(q, pageable);
    }

    public movie_cast findById(movie_cast_id id) {
        return movieCastRepository.findById(id).orElse(null);
    }

    public List<movie_cast> findByMovieId(Integer movieId) {
        return movieCastRepository.findByMovieId(movieId);
    }

    public Page<movie_cast> findByMovieIdPaginated(Integer movieId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieCastRepository.findByMovieIdPaginated(movieId, pageable);
    }

    public void save(movie_cast movieCast) {
        movieCastRepository.save(movieCast);
    }

    public void delete(movie_cast_id id) {
        movieCastRepository.deleteById(id);
    }
}