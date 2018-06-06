package com.example.service.api;

import com.example.dto.CustomerDto;

import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDto> getCustomerById(String id);
}
