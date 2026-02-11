package com.example.practicatres.services;
import com.example.practicatres.models.entities.movie;
import com.example.practicatres.repository.movieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class movieService {
    @Autowired
    movieRepo movieRepository;

    public Page<movie> searchMoviesPaginated(String title, String actorOrCharacter, String genre, String director, Integer year, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        boolean hasTitle = title != null && !title.trim().isEmpty();
        boolean hasActor = actorOrCharacter != null && !actorOrCharacter.trim().isEmpty();
        boolean hasGenre = genre != null && !genre.trim().isEmpty();
        boolean hasDirector = director != null && !director.trim().isEmpty();
        boolean hasYear = year != null;

        if (hasYear && hasGenre) {
            return movieRepository.findByGenreAndYear(genre, year, pageable);
        }
        if (hasYear && hasDirector) {
            return movieRepository.findByDirectorAndYear(director, year, pageable);
        }

        if (hasActor) {
            return movieRepository.findByActorOrCharacterName(actorOrCharacter, pageable);
        }
        if (hasGenre) {
            return movieRepository.findByGenreName(genre, pageable);
        }
        if (hasDirector) {
            return movieRepository.findByDirectorName(director, pageable);
        }
        if (hasYear) {
            return movieRepository.findByReleaseYear(year, pageable);
        }
        if (hasTitle) {
            return movieRepository.findByTitleContainingIgnoreCase(title, pageable);
        }
        return movieRepository.findAll(pageable);
    }

    public List<movie> findAll() {
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
