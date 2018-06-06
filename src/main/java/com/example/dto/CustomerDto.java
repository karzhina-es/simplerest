package com.example.dto;

import com.example.domain.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private String id;
    private String fullName;
    private BigDecimal balance;
    private CustomerStatus status;
    private String login;
    private List<PartnerMappingDto> partnerMappings;
}
