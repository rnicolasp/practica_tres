package com.example.practicatres.services;

import com.example.practicatres.models.entities.User;
import com.example.practicatres.models.entities.Role;
import com.example.practicatres.repository.UserRepository;
import com.example.practicatres.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        if (user.getUser_id() == null || user.getUser_id() == 0) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            User existing = userRepository.findById(user.getUser_id()).orElse(null);
            if (existing != null) {
                if (user.getPassword() == null || user.getPassword().isEmpty()) {
                    user.setPassword(existing.getPassword());
                } else {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                }
            } else {
                if (user.getPassword() != null) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                }
            }
            return userRepository.save(user);
        }
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAllPaginated(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void assignRole(Integer userId, Integer roleId) {
        User user = findById(userId);
        Role role = roleRepository.findById(roleId).orElse(null);
        if (user != null && role != null) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    public void removeRole(Integer userId, Integer roleId) {
        User user = findById(userId);
        Role role = roleRepository.findById(roleId).orElse(null);
        if (user != null && role != null) {
            user.getRoles().remove(role);
            userRepository.save(user);
        }
    }

    public void initializeDefaultRoles() {
        if (roleRepository.findByRoleName("USER").isEmpty()) {
            Role userRole = new Role("USER", "Usuario estándar");
            roleRepository.save(userRole);
        }
        if (roleRepository.findByRoleName("ADMIN").isEmpty()) {
            Role adminRole = new Role("ADMIN", "Administrador");
            roleRepository.save(adminRole);
        }
    }

    public void createDefaultAdmin() {
        if (findByUsername("admin") == null) {
            initializeDefaultRoles();
            User admin = new User("admin", "1234");
            admin.setEnabled(true);
            Role adminRole = roleRepository.findByRoleName("ADMIN").orElse(null);
            if (adminRole != null) {
                admin.getRoles().add(adminRole);
            }
            save(admin);
        }
    }

    @PostConstruct
    public void init() {
        initializeDefaultRoles();
        createDefaultAdmin();
    }
}
