package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.genre;
import com.example.practicatres.services.genreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String guardarGenero(@RequestParam String nombreGenero) {
        if (nombreGenero != null && !nombreGenero.trim().isEmpty()) {
            genre nuevo = new genre();
            nuevo.setGenre_name(nombreGenero);
            genreService.save(nuevo);
        }
        return "redirect:/genre";
    }

    @GetMapping("/genre/editar/{id}")
    public String editarGenero(@RequestParam Integer id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "genre_form";
    }

    @GetMapping("/genre/eliminar/{id}")
    public String eliminarGenero(@RequestParam Integer id) {
        genreService.delete(id);
        return "redirect:/genre";
    }
}