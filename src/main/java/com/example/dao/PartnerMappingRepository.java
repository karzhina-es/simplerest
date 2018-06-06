package com.example.dao;

import com.example.domain.PartnerMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerMappingRepository extends JpaRepository<PartnerMapping, String> {

    List<PartnerMapping> getByCustomer_Id(String customerId);
}
