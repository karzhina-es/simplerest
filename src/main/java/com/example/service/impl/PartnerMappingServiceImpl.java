package com.example.service.impl;

import com.example.dao.CustomerRepository;
import com.example.dao.PartnerMappingRepository;
import com.example.domain.PartnerMapping;
import com.example.dto.ProcessPartnerMappingDto;
import com.example.service.api.PartnerMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerMappingServiceImpl implements PartnerMappingService {

    private final CustomerRepository customerRepository;
    private final PartnerMappingRepository partnerMappingRepository;

    @Autowired
    public PartnerMappingServiceImpl(CustomerRepository customerRepository, PartnerMappingRepository partnerMappingRepository) {
        this.customerRepository = customerRepository;
        this.partnerMappingRepository = partnerMappingRepository;
    }

    @Override
    public List<PartnerMapping> getMappingsByCustomerId(String customerId) {
        return partnerMappingRepository.getByCustomer_Id(customerId);
    }

    @Override
    public Optional<PartnerMapping> getMappingById(String mappingId) {
        return partnerMappingRepository.findById(mappingId);
    }

    @Override
    public PartnerMapping processMapping(String customerId, String mappingId, ProcessPartnerMappingDto mappingDto) {
        return partnerMappingRepository.save(processPartnerMapping(customerId, mappingId, mappingDto));
    }

    @Override
    public void deleteMapping(String mappingId) {
        partnerMappingRepository.delete(partnerMappingRepository.getOne(mappingId));
    }

    private PartnerMapping processPartnerMapping(String customerId, String mappingId, ProcessPartnerMappingDto
            mappingDto) {

        PartnerMapping mapping;
        if (mappingId != null) {
            mapping = partnerMappingRepository.getOne(mappingId);
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
