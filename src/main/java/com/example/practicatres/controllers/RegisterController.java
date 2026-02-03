package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.User;
import com.example.practicatres.models.entities.Role;
import com.example.practicatres.services.UserService;
import com.example.practicatres.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String registroForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/guardar")
    public String guardarRegistro(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "El usuario ya existe");
            model.addAttribute("user", new User());
            return "register";
        }

        if (user.getPassword() == null || user.getPassword().length() < 4) {
            model.addAttribute("error", "La contraseña debe tener al menos 4 caracteres");
            model.addAttribute("user", new User());
            return "register";
        }

        user.setEnabled(true);
        
        Role userRole = roleRepository.findByRoleName("USER").orElse(null);
        if (userRole != null) {
            user.getRoles().add(userRole);
        }

        userService.save(user);
        model.addAttribute("success", "Usuario registrado exitosamente. Por favor, inicia sesión.");
        model.addAttribute("user", new User());
        return "register";
    }
}
