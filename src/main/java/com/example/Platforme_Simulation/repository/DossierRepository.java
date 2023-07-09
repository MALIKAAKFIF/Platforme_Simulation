package com.example.Platforme_Simulation.repository;



import com.example.Platforme_Simulation.entity.Dossier;
import com.example.Platforme_Simulation.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    @Query(value = "SELECT code_agence FROM rad_codeagence WHERE radical = :radical", nativeQuery = true)
    int findCodeAgenceByRadical(@Param("radical") int radical);

    @Query(value = "SELECT * FROM dossier WHERE utilisateur_id = :utilisateurId", nativeQuery = true)
    List<Dossier> findByUtilisateurId(@Param("utilisateurId") Long utilisateurId);

    @Query(value = "SELECT id FROM dossier WHERE demande_credit_id = :demandeCreditId", nativeQuery = true)
    Long findDossierIdByDemandeId(@Param("demandeCreditId") Long demandeCreditId);

    @Query(value = "SELECT id FROM dossier WHERE utilisateur_id = :utilisateurId", nativeQuery = true)
    List<Long> findIdByUtilisateurId(@Param("utilisateurId") Long utilisateurId);
    boolean existsByUtilisateur(Utilisateur utilisateur);

    long count();


}

