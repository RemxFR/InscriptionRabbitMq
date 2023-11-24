package com.fr.inscriptionrabbitmq.dto.service;

import org.example.UtilisateurRabbitMQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey.name}")
    private String routingKey;

    @Value("${rabbitmq.json.routingkey.name}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    public void sendJsonMessage(UtilisateurRabbitMQ utilisateurRabbitMQ) {
        LOGGER.info(String.format("Json message envoyé ! -> %s", utilisateurRabbitMQ.toString()));
        rabbitTemplate.convertAndSend(exchangeName, routingJsonKey, utilisateurRabbitMQ);
    }
}
