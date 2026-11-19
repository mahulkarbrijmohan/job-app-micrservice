package com.microservice.jobservice.clients;

import com.microservice.jobservice.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE", fallback = ReviewClientFallback.class)
public interface ReviewClient {

    @GetMapping("/api/v1/reviews")
    List<Review> getReviewsByCompany(@RequestParam("companyId") Long companyId);
}
