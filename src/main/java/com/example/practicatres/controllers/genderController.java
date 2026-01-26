package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.gender;
import com.example.practicatres.services.genderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class genderController {

    @Autowired
    genderService genderService;

    @GetMapping("/gender")
    public String verGenders(Model model) {
        List<gender> genders = genderService.findAll();
        model.addAttribute("genders", genders);
        return "gender";
    }

    @PostMapping("/genders/guardar")
    public String guardarGender(@RequestParam String nombreGender, @RequestParam(required = false) Integer gender_id) {
        gender g;
        if (gender_id != null) {
            g = genderService.findById(gender_id);
        } else {
            g = new gender();
        }
        if (nombreGender != null && !nombreGender.trim().isEmpty()) {
            g.setGender(nombreGender);
            genderService.save(g);
        }
        return "redirect:/gender";
    }

    @GetMapping("/gender/editar/{id}")
    public String editarGender(@PathVariable Integer id, Model model) {
        model.addAttribute("gender", genderService.findById(id));
        return "gender_editar";
    }

    @GetMapping("/gender/eliminar/{id}")
    public String eliminarGender(@PathVariable Integer id) {
        genderService.delete(id);
        return "redirect:/gender";
    }
}