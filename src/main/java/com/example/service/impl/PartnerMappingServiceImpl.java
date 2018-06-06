package com.example.service.impl;

import com.example.converter.PartnerMappingConverter;
import com.example.dao.CustomerRepository;
import com.example.dao.PartnerMappingRepository;
import com.example.domain.PartnerMapping;
import com.example.dto.PartnerMappingDto;
import com.example.dto.ProcessPartnerMappingDto;
import com.example.service.api.PartnerMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartnerMappingServiceImpl implements PartnerMappingService {

    private final CustomerRepository customerRepository;
    private final PartnerMappingRepository mappingRepository;
    private final PartnerMappingConverter mappingConverter;

    @Autowired
    public PartnerMappingServiceImpl(CustomerRepository customerRepository, PartnerMappingRepository mappingRepository, PartnerMappingConverter mappingConverter) {
        this.customerRepository = Objects.requireNonNull(customerRepository);
        this.mappingRepository = Objects.requireNonNull(mappingRepository);
        this.mappingConverter = Objects.requireNonNull(mappingConverter);
    }

    @Override
    public List<PartnerMappingDto> getMappingsByCustomerId(String customerId) {
        return mappingConverter.convert(mappingRepository.getByCustomer_Id(customerId));
    }

    @Override
    public Optional<PartnerMappingDto> getMappingById(String mappingId) {
        return mappingRepository.findById(mappingId).map(mappingConverter::convert);
    }

    @Override
    public PartnerMappingDto processMapping(String customerId, String mappingId, ProcessPartnerMappingDto mappingDto) {
        return mappingConverter.convert(mappingRepository.save(processPartnerMapping(customerId, mappingId, mappingDto)));
    }

    @Override
    public void deleteMapping(String mappingId) {
        mappingRepository.delete(mappingRepository.getOne(mappingId));
    }

    private PartnerMapping processPartnerMapping(String customerId, String mappingId, ProcessPartnerMappingDto mappingDto) {

        PartnerMapping mapping;
        if (mappingId != null) {
            mapping = mappingRepository.getOne(mappingId);
        } else {
            mapping = new PartnerMapping();
            mapping.setCustomer(customerRepository.getOne(customerId));
        }

        mapping.setPartnerId(mappingDto.getPartnerId());
        mapping.setCustomerIdInPartnerSystem(mappingDto.getCustomerIdInPartnerSystem());
        mapping.setFullName(mappingDto.getFullName());
        mapping.setPicture(mappingDto.getPicture());
        return mapping;
    }
}
