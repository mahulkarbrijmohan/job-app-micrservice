package com.microservice.companyservice.repository;

import com.microservice.companyservice.modal.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
