package com.jlbcompany.app.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// this will change when we move to Kubernetes
@FeignClient(
        name = "fraud",
        url = "${clients.fraud.url}"
)
public interface FraudClient {

    // taken from Fraud Controller
    // this is an interface that will target fraud controller
    // same thing but without the method body, openfeign in action
    // any microservice that wants to talk to fraud just need to use this interface
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId);

}
