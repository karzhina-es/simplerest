package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends BaseDomain {

    private String fullName;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
    private String login;
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<PartnerMapping> partnerMappings;

}
