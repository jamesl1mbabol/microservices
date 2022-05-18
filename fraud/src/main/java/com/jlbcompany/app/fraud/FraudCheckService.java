package com.jlbcompany.app.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    // check took place store in database
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    // in between builder and build, set i.e. .isFraudster(false)
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        // always return false for demo purposes, usually 3rd party app
        return false;
    }
}
