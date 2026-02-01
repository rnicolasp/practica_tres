package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.*;
import com.example.practicatres.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;

@Controller
public class movieCastController {
    @Autowired
    movieCastService movieCastService;

    @Autowired
    movieService movieService;

    @Autowired
    personService personService;

    @Autowired
    genderService genderService;

    @GetMapping("/moviecast")
    public String listMovieCast(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "40") int size,
                                @RequestParam(required = false) String q) {
        Page<movie_cast> pageResult = movieCastService.searchPaginated(q, page, size);
        model.addAttribute("castsPage", pageResult);
        model.addAttribute("casts", pageResult.getContent());
        model.addAttribute("q", q);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "moviecast";
    }

    @GetMapping("/moviecast/nuevo")
    public String nuevoMovieCast(Model model) {
        model.addAttribute("moviecast", new movie_cast());
        List<movie> movies = movieService.findAll();
        List<person> persons = personService.findAll();
        List<gender> genders = genderService.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("persons", persons);
        model.addAttribute("genders", genders);
        model.addAttribute("tituloPagina", "Asignar Actor a Película");
        return "moviecast_editar";
    }

    @PostMapping("/moviecast/guardar")
    public String guardarMovieCast(
            @RequestParam Integer movie_id,
            @RequestParam Integer person_id,
            @RequestParam Integer gender_id,
            @RequestParam String character_name,
            @RequestParam(required = false) Integer cast_order) {
        
        movie_cast movieCast = new movie_cast();
        movie_cast_id id = new movie_cast_id();
        id.setMovie_id(movie_id);
        id.setPerson_id(person_id);
        id.setGender_id(gender_id);
        movieCast.setId(id);
        movieCast.setMovie(movieService.findById(movie_id));
        movieCast.setPerson(personService.findById(person_id));
        movieCast.setGender(genderService.findById(gender_id));
        movieCast.setCharacter_name(character_name);
        if (cast_order != null) {
            movieCast.setCast_order(cast_order);
        }

        movieCastService.save(movieCast);
        return "redirect:/moviecast";
    }

    @GetMapping("/moviecast/eliminar/{movie_id}/{person_id}/{gender_id}")
    public String eliminarMovieCast(
            @PathVariable Integer movie_id,
            @PathVariable Integer person_id,
            @PathVariable Integer gender_id) {
        
        movie_cast_id id = new movie_cast_id();
        id.setMovie_id(movie_id);
        id.setPerson_id(person_id);
        id.setGender_id(gender_id);
        movieCastService.delete(id);
        return "redirect:/moviecast";
    }
}
