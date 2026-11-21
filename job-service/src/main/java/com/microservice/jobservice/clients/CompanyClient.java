package com.microservice.jobservice.clients;

import com.microservice.jobservice.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE",url = "${company-service.url}" ,fallback = CompanyClientFallback.class)
public interface CompanyClient {

    @GetMapping("/api/v1/companies/{id}")
    Company getCompanyById(@PathVariable("id") Long id);
}
