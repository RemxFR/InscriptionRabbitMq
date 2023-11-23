package com.fr.inscriptionrabbitmq.dto.service;

import com.fr.inscriptionrabbitmq.entity.Utilisateur;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

@Service
public class RabbitMqService {

    private static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static RabbitMqService rabbitMqService;
    private static ConnectionFactory connectionFactory;
    private static Connection connection;
    private static Channel channel;
    private static final String host = "localhost";
    private static final int port = 15672;

    @Value("${rabbitmq.usrname}")
    private static String username;
    @Value("${rabbitmq.password}")
    private static String password;
    private static String inscriptionQueue = "inscription_queue";
    private static String mailUtilisateurMessageField = "Mail utilisateur";

    private RabbitMqService() {
        this.connexionRabbitMq();
    }

    public static RabbitMqService rabbitMqServiceSingleton() {
        if (rabbitMqService != null) {
            rabbitMqService = new RabbitMqService();
        }
        return rabbitMqService;
    }

    private static void connexionRabbitMq() {
        log.info("Démarrage de la connexion à rabbit mq");
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(host);
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            connectionFactory.setPort(15672);
            connectionFactory.setUsername(username);
            connectionFactory.setPassword(password);

            channel.queueDeclare(inscriptionQueue, false, false, false, null);
            log.info("Connexion à rabbit mq réussie !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendRabbitMqMessage(Utilisateur utilisateur) {
        log.info("Début de l'envoie du message vers RabbitMq...");

        if (channel != null) {
            try {
                if (utilisateur.getMail() != null || !utilisateur.getMail().equals("")) {
                    channel.basicPublish(mailUtilisateurMessageField, inscriptionQueue, null, utilisateur.getMail().getBytes());
                    log.info("Message envoyé vers RabbitMq...");
                } else {
                    log.info("Echec de l'envoi, mail de l'utilisateur vide ou incorrect");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (channel.isOpen() && connection.isOpen()) {
                    try {
                        channel.close();
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
