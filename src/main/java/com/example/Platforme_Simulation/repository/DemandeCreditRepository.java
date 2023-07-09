package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.DemandeCredit;
import com.example.Platforme_Simulation.entity.Dossier;
import com.example.Platforme_Simulation.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DemandeCreditRepository extends JpaRepository<DemandeCredit, Integer> {
    Optional<DemandeCredit> findById(int id);
    @Query(value = "SELECT * FROM demande_credit WHERE utilisateur_id = :utilisateurId", nativeQuery = true)
    List<DemandeCredit> findDemandeByUtilisateurId(@Param("utilisateurId") Long utilisateurId);
    @Query(value = "SELECT * FROM demande_credit WHERE id_demande = :demandeId", nativeQuery = true)
    DemandeCredit findDemandeByDemandeId(@Param("demandeId") Long demandeId);
    long countByStatut(String statut);
    DemandeCredit findTopByUtilisateurOrderByDateDemandeDesc(Utilisateur utilisateur);


}
