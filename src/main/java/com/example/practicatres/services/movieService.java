package com.example.practicatres.services;

import com.example.practicatres.models.entities.movie;
import com.example.practicatres.repository.movieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class movieService {
    @Autowired
    movieRepo movieRepository;

    public List<movie> searchMovies(String title, String actor, String genre) {
        if (title != null && !title.isEmpty()) {
            return movieRepository.findByTitleContainingIgnoreCase(title);
        }
        if (actor != null && !actor.isEmpty()) {
            return movieRepository.findByActorOrCharacterName(actor);
        }
        if (genre != null && !genre.isEmpty()) {
            return movieRepository.findByGenreName(genre);
        }
        return movieRepository.findAll();
    }

    public void save(movie m) {
        movieRepository.save(m);
    }

    public movie findById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        movieRepository.deleteById(id);
    }
}
