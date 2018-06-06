package com.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "partner_mappings")
@Getter
@Setter
public class PartnerMapping extends BaseDomain {

    private String partnerId;
    private String customerIdInPartnerSystem;
    private String fullName;
    private String picture;
    @ManyToOne
    private Customer customer;

}
