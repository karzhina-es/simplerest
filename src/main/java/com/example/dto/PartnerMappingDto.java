package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerMappingDto {

    private String id;
    private String partnerId;
    private String customerIdInPartnerSystem;
    private String fullName;
    private String picture;
    private String customerId;
}
