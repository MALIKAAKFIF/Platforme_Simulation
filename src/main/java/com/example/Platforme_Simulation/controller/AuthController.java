package com.example.Platforme_Simulation.controller;


import com.example.Platforme_Simulation.dto.UtilisateurDTO;
import com.example.Platforme_Simulation.entity.Utilisateur;
import com.example.Platforme_Simulation.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UtilisateurDTO utilisateur = new UtilisateurDTO();
        model.addAttribute("utilisateur", utilisateur);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("utilisateur") UtilisateurDTO utilisateur,
                               BindingResult result,
                               Model model) {
        Utilisateur existing = utilisateurService.findByEmail(utilisateur.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Il existe déjà un compte enregistré avec cet e-mail");
        }
        if (result.hasErrors()) {
            model.addAttribute("utilisateur", utilisateur);
            return "register";
        }
        utilisateurService.saveUtilisateur(utilisateur);
        return "redirect:/register?success";
    }


}

