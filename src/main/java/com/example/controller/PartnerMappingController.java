package com.example.controller;

import com.example.domain.Customer;
import com.example.domain.PartnerMapping;
import com.example.dto.CustomerDto;
import com.example.dto.PartnerMappingDto;
import com.example.dto.ProcessPartnerMappingDto;
import com.example.service.api.CustomerService;
import com.example.service.api.FileService;
import com.example.service.api.PartnerMappingService;
import com.example.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers/{customerId}")
@PreAuthorize("'@me'.equals(#customerId) || #customerId.equals(authentication.name)")
public class PartnerMappingController {

    private final PartnerMappingService partnerMappingService;
    private final CustomerService customerService;
    private final FileService fileService;
    private final AuthenticationUtils authUtils;

    @Autowired
    public PartnerMappingController(PartnerMappingService partnerMappingService, CustomerService customerService, FileService fileService, AuthenticationUtils authUtils) {
        this.partnerMappingService = Objects.requireNonNull(partnerMappingService);
        this.customerService = Objects.requireNonNull(customerService);
        this.fileService = Objects.requireNonNull(fileService);
        this.authUtils = Objects.requireNonNull(authUtils);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Optional<CustomerDto> getById(@PathVariable String customerId) {
        return customerService.getCustomerById(getCustomerId(customerId));
    }

    @RequestMapping(value = "/mappings", method = RequestMethod.GET)
    public List<PartnerMappingDto> getMappingsByCustomerId(@PathVariable String customerId) {
        return partnerMappingService.getMappingsByCustomerId(getCustomerId(customerId));
    }

    @RequestMapping(value = "/mappings/{mappingId}", method = RequestMethod.GET)
    public Optional<PartnerMappingDto> getMappingById(@PathVariable String customerId, @PathVariable String mappingId) {
        return partnerMappingService.getMappingById(mappingId);
    }

    @RequestMapping(value = "/mappings", method = RequestMethod.POST)
    public PartnerMappingDto addMapping(@PathVariable String customerId, @RequestBody ProcessPartnerMappingDto partnerMapping) {
        return partnerMappingService.processMapping(getCustomerId(customerId), null, partnerMapping);
    }

    @RequestMapping(value = "/mappings/{mappingId}", method = RequestMethod.PUT)
    public PartnerMappingDto updateMapping(@PathVariable String customerId, @PathVariable String mappingId, @RequestBody ProcessPartnerMappingDto partnerMapping) {
        return partnerMappingService.processMapping(getCustomerId(customerId), mappingId, partnerMapping);
    }

    @RequestMapping(value = "/mappings/{mappingId}", method = RequestMethod.DELETE)
    public void deleteMapping(@PathVariable String customerId, @PathVariable String mappingId) {
        partnerMappingService.deleteMapping(mappingId);
    }

    @RequestMapping(value = "/pictures", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String customerId) {
        return fileService.saveFile(file);
    }

    private String getCustomerId(String id) {
        if (id.equalsIgnoreCase("@me")) {
            return authUtils.getCurrentAuthenticationName();
        } else {
            return id;
        }
    }

}
