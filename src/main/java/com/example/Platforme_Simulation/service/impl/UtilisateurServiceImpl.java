package com.example.Platforme_Simulation.service.impl;

import com.example.Platforme_Simulation.dto.UtilisateurDTO;
import com.example.Platforme_Simulation.entity.Role;
import com.example.Platforme_Simulation.entity.Utilisateur;
import com.example.Platforme_Simulation.repository.RoleRepository;
import com.example.Platforme_Simulation.repository.UtilisateurRepository;
import com.example.Platforme_Simulation.service.UtilisateurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  RoleRepository roleRepository,
                                  PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = createRole("ROLE_USER");
        }

        utilisateur.setRoles(Collections.singletonList(userRole));
        utilisateurRepository.save(utilisateur);
    }



    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public List<UtilisateurDTO> findAllUsers() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UtilisateurDTO convertEntityToDto(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setEmail(utilisateur.getEmail());

        return utilisateurDTO;
    }

    private Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
    private Role checkRoleExist(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        return role;
    }
}
