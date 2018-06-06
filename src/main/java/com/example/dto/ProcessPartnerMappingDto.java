package com.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProcessPartnerMappingDto {

    private String partnerId;
    private String customerIdInPartnerSystem;
    private String fullName;
    private String picture;
}
