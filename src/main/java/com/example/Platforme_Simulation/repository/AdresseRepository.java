package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.Adresse;
import com.example.Platforme_Simulation.entity.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    @Query(value = "SELECT * FROM adresse WHERE utilisateur_id = :utilisateurId", nativeQuery = true)
    Adresse findAdrByUtilisateurId(@Param("utilisateurId") Long utilisateurId);
}
