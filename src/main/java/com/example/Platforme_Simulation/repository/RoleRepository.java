package com.example.Platforme_Simulation.repository;


import com.example.Platforme_Simulation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
