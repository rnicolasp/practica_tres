package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.language;
import com.example.practicatres.services.languageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class languageController {

    @Autowired
    languageService languageService;

    @GetMapping("/language")
    public String verLanguages(Model model) {
        List<language> languages = languageService.findAll();
        model.addAttribute("languages", languages);
        return "language";
    }

    @PostMapping("/languages/guardar")
    public String guardarLanguage(@RequestParam String nombreLanguage, @RequestParam String code, @RequestParam(required = false) Integer language_id) {
        language l;
        if (language_id != null) {
            l = languageService.findById(language_id);
        } else {
            l = new language();
        }
        if (nombreLanguage != null && !nombreLanguage.trim().isEmpty() && code != null && !code.trim().isEmpty()) {
            l.setLanguage_name(nombreLanguage);
            l.setLanguage_code(code);
            languageService.save(l);
        }
        return "redirect:/language";
    }

    @GetMapping("/language/editar/{id}")
    public String editarLanguage(@PathVariable Integer id, Model model) {
        model.addAttribute("language", languageService.findById(id));
        return "language_editar";
    }

    @GetMapping("/language/eliminar/{id}")
    public String eliminarLanguage(@PathVariable Integer id) {
        languageService.delete(id);
        return "redirect:/language";
    }
}