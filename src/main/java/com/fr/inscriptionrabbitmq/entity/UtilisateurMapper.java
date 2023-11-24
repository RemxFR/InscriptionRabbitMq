package com.fr.inscriptionrabbitmq.entity;

import org.example.UtilisateurRabbitMQ;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UtilisateurMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "prenom", target = "prenom")
    @Mapping(source = "mail", target = "mail")
    UtilisateurRabbitMQ utilisateurToUtilisateurRabbitMQMapping(Utilisateur utilisateur);
}
