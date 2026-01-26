package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.movie;
import com.example.practicatres.services.movieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class movieController {
    @Autowired
    movieService movieService;

    @GetMapping("/movies")
    public String listMovies(@RequestParam(required = false) String title, @RequestParam(required = false) String actor, @RequestParam(required = false) String genre, Model model) {

        List<movie> movies = movieService.searchMovies(title, actor, genre);
        model.addAttribute("movies", movies);

        return "movies";
    }
}
