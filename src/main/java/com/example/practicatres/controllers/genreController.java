package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.genre;
import com.example.practicatres.services.genreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class genreController {

    @Autowired
    genreService genreService;

    @GetMapping("/genre")
    public String verGeneros(Model model) {
        List<genre> genre = genreService.findAll();
        model.addAttribute("genre", genre);
        return "genre";
    }

    @PostMapping("/generos/guardar")
    public String guardarGenero(@RequestParam String nombreGenero, @RequestParam(required = false) Integer genre_id) {
        genre g;
        if (genre_id != null) {
            g = genreService.findById(genre_id);
        } else {
            g = new genre();
        }
        if (nombreGenero != null && !nombreGenero.trim().isEmpty()) {
            g.setGenre_name(nombreGenero);
            genreService.save(g);
        }
        return "redirect:/genre";
    }

    @GetMapping("/genre/editar/{id}")
    public String editarGenero(@PathVariable Integer id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "genre_editar";
    }

    @GetMapping("/genre/eliminar/{id}")
    public String eliminarGenero(@PathVariable Integer id) {
        genreService.delete(id);
        return "redirect:/genre";
    }
}