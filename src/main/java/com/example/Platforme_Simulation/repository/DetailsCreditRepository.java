package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.DemandeCredit;
import com.example.Platforme_Simulation.entity.DetailsCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailsCreditRepository extends JpaRepository<DetailsCredit, Integer> {
    @Query(value = "SELECT * FROM details_credit WHERE demande_credit_id = :demandeCreditId", nativeQuery = true)
    DetailsCredit findDetailsCreditByDemandeCredit(@Param("demandeCreditId") Long utilisateurId);
}
