package com.jlbcompany.app.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
