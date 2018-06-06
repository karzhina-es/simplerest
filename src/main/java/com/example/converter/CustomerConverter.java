package com.example.converter;

import com.example.domain.Customer;
import com.example.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerConverter extends AbstractConverter<Customer, CustomerDto> {

    private final PartnerMappingConverter mappingConverter;

    @Autowired
    public CustomerConverter(PartnerMappingConverter mappingConverter) {
        this.mappingConverter = Objects.requireNonNull(mappingConverter);
    }

    @Override
    public CustomerDto convert(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFullName(customer.getFullName());
        dto.setLogin(customer.getLogin());
        dto.setStatus(customer.getStatus());
        dto.setBalance(customer.getBalance());
        dto.setPartnerMappings(mappingConverter.convert(customer.getPartnerMappings()));
        return dto;
    }
}
