package com.example.Platforme_Simulation.repository;

import com.example.Platforme_Simulation.entity.DocumentSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentSupportRepository extends JpaRepository<DocumentSupport, Long> {


    DocumentSupport findByDocumentId(Long documentId);

    @Query("SELECT d FROM DocumentSupport d WHERE d.idDemande = :demandeId")
    List<DocumentSupport> findIdDocumentByIdDemande(@Param("demandeId") Long demandeId);




}


