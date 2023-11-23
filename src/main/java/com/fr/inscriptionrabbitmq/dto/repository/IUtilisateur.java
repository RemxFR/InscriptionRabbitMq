package com.fr.inscriptionrabbitmq.dto.repository;

import com.fr.inscriptionrabbitmq.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtilisateur extends JpaRepository<Utilisateur, Integer> {
}
