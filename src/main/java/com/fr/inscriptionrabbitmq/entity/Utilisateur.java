package com.fr.inscriptionrabbitmq.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_utilisateur")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom", length = 25)
    private String nom;

    @Column(name = "prenom", length = 25)
    private String prenom;

    @Column(name = "mail", length = 25)
    private String mail;

    @Column(name = "isEmailConfirmed", columnDefinition = "false")
    private Boolean isEmailConfirmed;

}
