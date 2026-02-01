package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.keyword;
import com.example.practicatres.services.keywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;

@Controller
public class keywordController {

    @Autowired
    keywordService keywordService;

    @GetMapping("/keyword")
    public String verKeywords(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "40") int size,
                              @RequestParam(required = false) String q) {
        Page<keyword> keywordPage = keywordService.findPaginated(page, size, q);
        model.addAttribute("keywordPage", keywordPage);
        model.addAttribute("keyword", keywordPage.getContent());
        model.addAttribute("q", q);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "keyword";
    }

    @PostMapping("/keyword/guardar")
    public String guardar(@ModelAttribute("newKeyword") keyword k) {
        if (k.getKeyword_name() != null && !k.getKeyword_name().trim().isEmpty()) {

            keywordService.save(k);
        }
        return "redirect:/keyword";
    }

    @GetMapping("/keyword/editar/{id}")
    public String editarKeyword(@PathVariable Integer id, Model model) {
        model.addAttribute("keyword", keywordService.findById(id));
        return "keyword_editar";
    }

    @GetMapping("/keyword/eliminar/{id}")
    public String eliminarKeyword(@PathVariable Integer id) {
        keywordService.delete(id);
        return "redirect:/keyword";
    }
}