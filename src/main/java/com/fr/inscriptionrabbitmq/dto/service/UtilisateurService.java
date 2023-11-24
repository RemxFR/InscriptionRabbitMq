package com.fr.inscriptionrabbitmq.dto.service;

import com.fr.inscriptionrabbitmq.dto.repository.IUtilisateur;
import com.fr.inscriptionrabbitmq.entity.Utilisateur;
import com.fr.inscriptionrabbitmq.entity.UtilisateurMapper;
import org.example.UtilisateurRabbitMQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private IUtilisateur iutilisateur;
    private RabbitMQProducer rabbitMQProducer;
    private UtilisateurMapper utilisateurMapper;
    private Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

    public UtilisateurService(
            IUtilisateur iutilisateur,
            RabbitMQProducer rabbitMQProducer,
            UtilisateurMapper utilisateurMapper) {
        this.iutilisateur = iutilisateur;
        this.rabbitMQProducer = rabbitMQProducer;
        this.utilisateurMapper = utilisateurMapper;
    }

    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {

        Utilisateur utilisateurInsrit = null;

        if (utilisateur != null) {
            utilisateurInsrit = this.iutilisateur.save(utilisateur);
            UtilisateurRabbitMQ utilisateurRabbitMQ = this.utilisateurMapper.utilisateurToUtilisateurRabbitMQMapping(utilisateurInsrit);
            this.rabbitMQProducer.sendJsonMessage(utilisateurRabbitMQ);
            LOGGER.info("Utilisateur envoyé via rabbitmq ! %s");
        }
        return utilisateurInsrit;
    }
}
