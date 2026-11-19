package com.microservice.jobservice.external;

import lombok.Data;

@Data
public class Company {
        private Long id;
        private String name;
        private String description;
        private long companyId;
        private long reviewId;
}
