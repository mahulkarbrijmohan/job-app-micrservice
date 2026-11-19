package com.microservice.jobservice.clients;

import com.microservice.jobservice.external.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewClientFallback implements ReviewClient {

    @Override
    public List<Review> getReviewsByCompany(Long companyId) {
        return List.of();
    }
}
