package com.example.practicatres.services;

import com.example.practicatres.models.entities.movie_crew;
import com.example.practicatres.models.entities.movie_crew_id;
import com.example.practicatres.repository.movieCrewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class movieCrewService {
    @Autowired
    movieCrewRepo movieCrewRepository;

    public List<movie_crew> findAll() {
        return movieCrewRepository.findAll();
    }

    public movie_crew findById(movie_crew_id id) {
        return movieCrewRepository.findById(id).orElse(null);
    }

    public List<movie_crew> findByMovieId(Integer movieId) {
        return movieCrewRepository.findByMovieId(movieId);
    }

    public void save(movie_crew movieCrew) {
        movieCrewRepository.save(movieCrew);
    }

    public void delete(movie_crew_id id) {
        movieCrewRepository.deleteById(id);
    }
}