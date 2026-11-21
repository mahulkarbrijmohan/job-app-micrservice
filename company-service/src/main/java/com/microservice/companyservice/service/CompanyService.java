package com.microservice.companyservice.service;



import com.microservice.companyservice.dto.ReviewMessage;
import com.microservice.companyservice.modal.Company;

import java.util.List;


public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);

    void updateCompany(ReviewMessage reviewObj);
}
