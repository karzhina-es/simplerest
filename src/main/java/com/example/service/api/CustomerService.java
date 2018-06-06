package com.example.service.api;

import com.example.domain.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomerById(String id);
}
