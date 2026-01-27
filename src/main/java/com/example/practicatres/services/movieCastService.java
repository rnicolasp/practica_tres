package com.example.practicatres.services;

import com.example.practicatres.models.entities.movie_cast;
import com.example.practicatres.models.entities.movie_cast_id;
import com.example.practicatres.repository.movieCastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class movieCastService {
    @Autowired
    movieCastRepo movieCastRepository;

    public List<movie_cast> findAll() {
        return movieCastRepository.findAll();
    }

    public movie_cast findById(movie_cast_id id) {
        return movieCastRepository.findById(id).orElse(null);
    }

    public List<movie_cast> findByMovieId(Integer movieId) {
        return movieCastRepository.findByMovieId(movieId);
    }

    public void save(movie_cast movieCast) {
        movieCastRepository.save(movieCast);
    }

    public void delete(movie_cast_id id) {
        movieCastRepository.deleteById(id);
    }
}