package com.example.Platforme_Simulation.service.impl;

import com.example.Platforme_Simulation.dto.AdminDTO;
import com.example.Platforme_Simulation.entity.*;
import com.example.Platforme_Simulation.repository.*;
import com.example.Platforme_Simulation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DossierRepository dossierRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    AdresseRepository adresseRepository;
    @Autowired
    DemandeCreditRepository demandeCreditRepository;
    @Autowired
    private RadCodeAgenceRepository radCodeAgenceRepository;
    @Autowired
    private DetailsCreditRepository detailsCreditRepository;

    public AdminServiceImpl(AdminRepository adminRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveAdmin(Admin adminDTO) {
        Admin admin = new Admin();

        admin.setMatricule(adminDTO.getMatricule());
        admin.setNom(adminDTO.getNom());
        admin.setPrenom(adminDTO.getPrenom());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        if (userRole == null) {
            userRole = createRole("ROLE_ADMIN");
        }


        adminRepository.save(admin);
    }

    @Override
    public Admin findByMatricule(String matricule) {
        return adminRepository.findByMatricule(matricule);
    }

    @Override
    public List<AdminDTO> findAllUsers() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private AdminDTO convertEntityToDto(Admin admin){
        AdminDTO  adminDTO = new AdminDTO();
        adminDTO.setMatricule(admin.getMatricule());


        return adminDTO;
    }

    private Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
    private Role checkRoleExist(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        return role;
    }
    @Autowired
    private RadCodeAgenceRepository RadCodeAgenceRepository;

    public List<String> getRadicauxByMatricule(String matricule) {
        Admin admin = adminRepository.findByMatricule(matricule);
        if (admin != null) {
            String codeAgenceAdmin = admin.getCode_agence();
            return radCodeAgenceRepository.findRadicauxByCodeAgence(codeAgenceAdmin);
        }
        return Collections.emptyList();
    }

    public List<Utilisateur> findUtilisateursByRadicaux(List<String> radicaux) {
        return utilisateurRepository.findByRadicalIn(radicaux);
    }
    public List<Dossier> getDossiersByUtilisateurId(Long utilisateurId) {
        return dossierRepository.findByUtilisateurId(utilisateurId);
    }
    public Adresse getAdresseByUtilisateurId(Long utilisateurId) {
        return adresseRepository.findAdrByUtilisateurId(utilisateurId);
    }

    public List<DemandeCredit> getDemandeByUtilisateurId(Long utilisateurId) {
        return demandeCreditRepository.findDemandeByUtilisateurId(utilisateurId);
    }

    public DetailsCredit getDetailsCreditByDemandeCreditId(Long demandeCreditId) {
        return detailsCreditRepository.findDetailsCreditByDemandeCredit(demandeCreditId);
    }

    public DemandeCredit updateDossierStatus(Long demandeId, String newStatus) {
        DemandeCredit demandeCredit = demandeCreditRepository.findDemandeByDemandeId(demandeId);
        demandeCredit.setStatut(newStatus);
        return demandeCreditRepository.save(demandeCredit);
    }
    public String getStatutDossier(Long id) {
        DemandeCredit demandeCredit = demandeCreditRepository.findById(Math.toIntExact(id)).orElse(null);
        return (demandeCredit != null) ? demandeCredit.getStatut() : "Inconnu";
    }

    public Long getDossierIdByDemandeCreditId(Long demandeCreditId) {
        return dossierRepository.findDossierIdByDemandeId(demandeCreditId);
    }
    public List<Long> getIdDossiersByUtilisateurId(Long utilisateurId) {
        return dossierRepository.findIdByUtilisateurId(utilisateurId);
    }

}
