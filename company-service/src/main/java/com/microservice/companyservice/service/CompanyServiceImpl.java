package com.microservice.companyservice.service;

import com.microservice.companyservice.modal.Company;
import com.microservice.companyservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {

        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company existingCompany = companyOptional.get();
            existingCompany.setName(company.getName());
            existingCompany.setDescription(company.getDescription());
            existingCompany.setName(company.getName());
            //existingCompany.setJobs(company.getJobs());
            companyRepository.save(existingCompany);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
         boolean existId = companyRepository.existsById(id);
         if (existId) {
            companyRepository.deleteById(id);
            return true;
         }else{
             return false;
         }
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.orElse(null);
    }
}
