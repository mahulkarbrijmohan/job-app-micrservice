package com.microservice.reviewservice.service;


import com.microservice.reviewservice.repository.ReviewRepo;
import com.microservice.reviewservice.modal.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;

    @Override
    public List<Review> getAllReviews(Long companyId) {
       return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepo.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepo.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if (review != null) {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepo.save(review);
                  return true;
               } else {
                    return false;
        }
            }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if (review != null
                && reviewRepo.existsById(reviewId)){
            reviewRepo.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }
}
