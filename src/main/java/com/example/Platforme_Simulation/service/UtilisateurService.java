package com.example.Platforme_Simulation.service;

import com.example.Platforme_Simulation.dto.UtilisateurDTO;
import com.example.Platforme_Simulation.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    void saveUtilisateur(UtilisateurDTO utilisateurDTO);



   Utilisateur findByEmail(String email);

    List<UtilisateurDTO> findAllUsers();


}
