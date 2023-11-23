package com.fr.inscriptionrabbitmq.dto.service;

import com.fr.inscriptionrabbitmq.dto.repository.IUtilisateur;
import com.fr.inscriptionrabbitmq.entity.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private IUtilisateur iutilisateur;

    public UtilisateurService(IUtilisateur iutilisateur) {
        this.iutilisateur = iutilisateur;
    }

    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {

        Utilisateur utilisateurInsrit = null;

        if (utilisateur != null) {
            utilisateurInsrit = this.iutilisateur.save(utilisateur);
            RabbitMqService.rabbitMqServiceSingleton();
            RabbitMqService.sendRabbitMqMessage(utilisateurInsrit);
        }
        return utilisateurInsrit;
    }
}
