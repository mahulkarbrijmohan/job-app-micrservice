package com.microservice.companyservice.controller;

import com.microservice.companyservice.modal.Company;
import com.microservice.companyservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @RequestBody Company company){
        boolean status = companyService.updateCompany(company, id);
        if(status){
            return new ResponseEntity<>("Company updated successfully",
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Company not found",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added successfully",
                HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
       boolean status = companyService.deleteCompanyById(id);
       if(status){
           return new ResponseEntity<>("Company deleted successfully",
                   HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Company not found",
                   HttpStatus.NOT_FOUND);
       }

    }


}
