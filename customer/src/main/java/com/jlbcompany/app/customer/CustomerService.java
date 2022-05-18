package com.jlbcompany.app.customer;

import com.jlbcompany.app.amqp.RabbitMQMessageProducer;
import com.jlbcompany.app.clients.fraud.FraudCheckResponse;
import com.jlbcompany.app.clients.fraud.FraudClient;
import com.jlbcompany.app.clients.notification.NotificationClient;
import com.jlbcompany.app.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    // inject restTemplate
    private final RestTemplate restTemplate;
    // inject FraudClient
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        // to have access to the customer id
        customerRepository.saveAndFlush(customer);
        //todo: check if fraudster

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        //todo: send notification
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName())
        );
        // this information leaves inside NotificationConfig and
        // application.yml (notification)
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}