package com.example.service.impl;

import com.example.converter.CustomerConverter;
import com.example.dao.CustomerRepository;
import com.example.dto.CustomerDto;
import com.example.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = Objects.requireNonNull(customerRepository);
        this.customerConverter = Objects.requireNonNull(customerConverter);
    }

    @Override
    public Optional<CustomerDto> getCustomerById(String id) {
        return customerRepository.findById(id).map(customerConverter::convert);
    }
}
