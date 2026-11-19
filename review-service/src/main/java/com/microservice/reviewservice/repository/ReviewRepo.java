package com.microservice.reviewservice.repository;


import com.microservice.reviewservice.modal.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByCompanyId(Long companyId);
}
