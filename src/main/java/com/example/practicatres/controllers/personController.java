package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.person;
import com.example.practicatres.services.personService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

@Controller
public class personController {
    @Autowired
    personService personService;

    @GetMapping("/person")
    public String listPersons(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String q) {
        Page<person> personsPage = personService.findPaginated(page, size, q);
        model.addAttribute("personsPage", personsPage);
        model.addAttribute("persons", personsPage.getContent());
        model.addAttribute("q", q);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "person";
    }

    @GetMapping("/person/nuevo")
    public String nuevaPersona(Model model) {
        model.addAttribute("person", new person());
        model.addAttribute("tituloPagina", "Nueva Persona");
        return "person_editar";
    }

    @GetMapping("/person/editar/{id}")
    public String editarPersona(@PathVariable Integer id, Model model) {
        person p = personService.findById(id);
        model.addAttribute("person", p);
        model.addAttribute("tituloPagina", "Editar Persona");
        return "person_editar";
    }

    @PostMapping("/person/guardar")
    public String guardarPersona(@ModelAttribute person p) {
        personService.save(p);
        return "redirect:/person";
    }

    @GetMapping("/person/eliminar/{id}")
    public String eliminarPersona(@PathVariable Integer id) {
        personService.delete(id);
        return "redirect:/person";
    }
}
