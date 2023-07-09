package com.example.Platforme_Simulation.service;

import com.example.Platforme_Simulation.entity.Notification;
import com.example.Platforme_Simulation.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
@Autowired
    NotificationRepository notificationRepository;


    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
    }
    public List<Notification> getNotificationsByDossierId(List<Long> dossierId) {
        return notificationRepository.findByDossierId(dossierId);
    }

}
