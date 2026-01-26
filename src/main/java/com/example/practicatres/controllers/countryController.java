package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.country;
import com.example.practicatres.services.countryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class countryController {

    @Autowired
    countryService countryService;

    @GetMapping("/country")
    public String verCountries(Model model) {
        List<country> countries = countryService.findAll();
        model.addAttribute("countries", countries);
        return "country";
    }

    @PostMapping("/countries/guardar")
    public String guardarCountry(@RequestParam String nombreCountry, @RequestParam String isoCode, @RequestParam(required = false) Integer country_id) {
        country c;
        if (country_id != null) {
            c = countryService.findById(country_id);
        } else {
            c = new country();
        }
        if (nombreCountry != null && !nombreCountry.trim().isEmpty() && isoCode != null && !isoCode.trim().isEmpty()) {
            c.setCountry_name(nombreCountry);
            c.setCountry_iso_code(isoCode);
            countryService.save(c);
        }
        return "redirect:/country";
    }

    @GetMapping("/country/editar/{id}")
    public String editarCountry(@PathVariable Integer id, Model model) {
        model.addAttribute("country", countryService.findById(id));
        return "country_editar";
    }

    @GetMapping("/country/eliminar/{id}")
    public String eliminarCountry(@PathVariable Integer id) {
        countryService.delete(id);
        return "redirect:/country";
    }
}