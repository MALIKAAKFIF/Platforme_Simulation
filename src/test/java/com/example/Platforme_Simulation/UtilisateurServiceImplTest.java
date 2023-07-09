package com.example.Platforme_Simulation;

import com.example.Platforme_Simulation.dto.UtilisateurDTO;
import com.example.Platforme_Simulation.entity.Role;
import com.example.Platforme_Simulation.entity.Utilisateur;
import com.example.Platforme_Simulation.repository.UtilisateurRepository;
import com.example.Platforme_Simulation.repository.RoleRepository;
import com.example.Platforme_Simulation.service.impl.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UtilisateurServiceImplTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        utilisateurService = new UtilisateurServiceImpl(utilisateurRepository, roleRepository, passwordEncoder);
    }

    @Test
    public void testSaveUtilisateur() {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNom("John");
        utilisateurDTO.setPrenom("Doe");
        utilisateurDTO.setEmail("john.doe@example.com");
        utilisateurDTO.setMotDePasse("password123");

        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(null);
        when(roleRepository.save(any(Role.class))).thenReturn(userRole);
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");

        utilisateurService.saveUtilisateur(utilisateurDTO);

        verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    public void testFindByEmail() {
        String email = "john.doe@example.com";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(email);

        when(utilisateurRepository.findByEmail(email)).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.findByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void testFindAllUsers() {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setNom("John");
        utilisateur1.setPrenom("Doe");
        utilisateur1.setEmail("john.doe@example.com");

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setNom("Jane");
        utilisateur2.setPrenom("Smith");
        utilisateur2.setEmail("jane.smith@example.com");

        List<Utilisateur> utilisateurs = List.of(utilisateur1, utilisateur2);

        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);

        List<UtilisateurDTO> result = utilisateurService.findAllUsers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNom());
        assertEquals("Jane", result.get(1).getNom());
    }
}
