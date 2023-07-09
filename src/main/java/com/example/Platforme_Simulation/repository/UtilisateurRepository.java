package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.Dossier;
import com.example.Platforme_Simulation.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
    Utilisateur findByEmail(String email);
    Utilisateur findByRadical(String radical);
    List<Utilisateur> findByRadicalIn(List<String> radicaux);
    @Query(value = "SELECT * FROM utilisateur WHERE email = :email", nativeQuery = true)
    Utilisateur findUtilisateurIdByEmail(@Param("email") String email);
}
