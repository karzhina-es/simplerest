package com.example.service.api;

import com.example.domain.PartnerMapping;
import com.example.dto.ProcessPartnerMappingDto;

import java.util.List;
import java.util.Optional;

public interface PartnerMappingService {

    List<PartnerMapping> getMappingsByCustomerId(String customerId);

    Optional<PartnerMapping> getMappingById(String mappingId);

    PartnerMapping processMapping(String customerId, String mappingId, ProcessPartnerMappingDto mapping);

    void deleteMapping(String mappingId);

}
