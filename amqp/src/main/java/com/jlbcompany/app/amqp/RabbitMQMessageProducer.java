package com.jlbcompany.app.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    //publish an object
    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        // to send to the Exchange
        // routing key binds to a particular queue
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    }

}

