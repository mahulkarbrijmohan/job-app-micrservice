package com.microservice.jobservice.dto;

import com.microservice.jobservice.external.Company;
import com.microservice.jobservice.external.Review;
import lombok.Data;

import java.util.List;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;
}
