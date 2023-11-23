package com.fr.inscriptionrabbitmq.dto.controller;

import com.fr.inscriptionrabbitmq.dto.service.UtilisateurService;
import com.fr.inscriptionrabbitmq.entity.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name = "utilisateur")
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping(name = "inscription")
    public ResponseEntity inscrireUnUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurInscrit = this.utilisateurService.inscrireUtilisateur(utilisateur);
        if (utilisateur == null) {
            return new ResponseEntity<>("Utilisateur non conforme ou erreur dans les informations données", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(utilisateurInscrit, HttpStatus.CREATED);
    }
}
