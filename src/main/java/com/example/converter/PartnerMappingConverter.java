package com.example.converter;

import com.example.domain.PartnerMapping;
import com.example.dto.PartnerMappingDto;
import org.springframework.stereotype.Component;

@Component
public class PartnerMappingConverter extends AbstractConverter<PartnerMapping, PartnerMappingDto> {

    @Override
    public PartnerMappingDto convert(PartnerMapping mapping) {
        PartnerMappingDto dto = new PartnerMappingDto();
        dto.setId(mapping.getId());
        dto.setFullName(mapping.getFullName());
        dto.setCustomerIdInPartnerSystem(mapping.getCustomerIdInPartnerSystem());
        dto.setPartnerId(mapping.getPartnerId());
        dto.setPicture(mapping.getPicture());
        dto.setCustomerId(mapping.getCustomer().getId());
        return dto;
    }
}
