package com.jlbcompany.app.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

//to have the ability to inject the RabbitMQMessageProducer
@SpringBootApplication(
        scanBasePackages = {
                "com.jlbcompany.app.customer",
                "com.jlbcompany.app.amqp",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.jlbcompany.app.clients"
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}

