package com.example.service.api;

import com.example.dto.PartnerMappingDto;
import com.example.dto.ProcessPartnerMappingDto;

import java.util.List;
import java.util.Optional;

public interface PartnerMappingService {

    List<PartnerMappingDto> getMappingsByCustomerId(String customerId);

    Optional<PartnerMappingDto> getMappingById(String mappingId);

    PartnerMappingDto processMapping(String customerId, String mappingId, ProcessPartnerMappingDto mapping);

    void deleteMapping(String mappingId);

}
