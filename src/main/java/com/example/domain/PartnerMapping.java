package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "partner_mappings")
@Getter
@Setter
@NoArgsConstructor
public class PartnerMapping extends BaseDomain {

    private String partnerId;
    private String customerIdInPartnerSystem;
    private String fullName;
    private String picture;
    @JsonIgnore
    @ManyToOne
    private Customer customer;

}
