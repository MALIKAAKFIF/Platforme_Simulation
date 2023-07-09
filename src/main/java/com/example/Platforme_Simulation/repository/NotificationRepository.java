package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.Dossier;
import com.example.Platforme_Simulation.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query(value = "SELECT * FROM notifications WHERE id_dossier = :dossierId", nativeQuery = true)
    List<Notification> findByDossierId(List <Long> dossierId);

}
