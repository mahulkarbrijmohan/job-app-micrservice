package com.microservice.jobservice.clients;

import com.microservice.jobservice.external.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyClientFallback implements CompanyClient {

    @Override
    public Company getCompanyById(Long id) {
        return null;
    }
}
