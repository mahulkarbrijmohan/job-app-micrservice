package com.microservice.jobservice.external;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private String title;
    private String description;
    private double rating;
    private long  companyId;
}
