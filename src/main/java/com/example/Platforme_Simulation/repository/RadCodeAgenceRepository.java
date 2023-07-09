package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.RadCodeAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RadCodeAgenceRepository extends JpaRepository<RadCodeAgence, Integer> {
    @Query(value = "SELECT radical FROM radCodeAgence WHERE code_agence = :codeAgence", nativeQuery = true)
    List<String> findRadicauxByCodeAgence(@Param("codeAgence") String codeAgence);
}
