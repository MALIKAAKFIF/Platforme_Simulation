package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.Admin;
import com.example.Platforme_Simulation.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

    Admin findByMatricule(String matricule);


}
