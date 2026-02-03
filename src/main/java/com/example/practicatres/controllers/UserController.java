package com.example.practicatres.controllers;

import com.example.practicatres.models.entities.User;
import com.example.practicatres.models.entities.Role;
import com.example.practicatres.services.UserService;
import com.example.practicatres.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           Model model) {
        Page<User> usersPage = userService.findAllPaginated(page, size);
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("usersPage", usersPage);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "user";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("tituloPagina", "Nuevo Usuario");
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "user_editar";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("tituloPagina", "Editar Usuario");
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "user_editar";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute User user, @RequestParam(required = false) Integer[] selectedRoles) {
        userService.save(user);
        
        if (selectedRoles != null) {
            user.getRoles().clear();
            for (Integer roleId : selectedRoles) {
                Role role = roleRepository.findById(roleId).orElse(null);
                if (role != null) {
                    user.getRoles().add(role);
                }
            }
            userService.save(user);
        }
        
        return "redirect:/user";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
