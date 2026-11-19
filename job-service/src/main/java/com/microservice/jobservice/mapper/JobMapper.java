package com.microservice.jobservice.mapper;

import com.microservice.jobservice.dto.JobDTO;
import com.microservice.jobservice.external.Company;
import com.microservice.jobservice.external.Review;
import com.microservice.jobservice.modal.JobTitle;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobDTO(
            JobTitle jobTitle , Company company , List<Review> reviews
    ){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(jobTitle.getId());
        jobDTO.setCompany(company);
        jobDTO.setTitle(jobTitle.getTitle());
        jobDTO.setDescription(jobTitle.getDescription());
        jobDTO.setLocation(jobTitle.getLocation());
        jobDTO.setMaxSalary(jobTitle.getMaxSalary());
        jobDTO.setMinSalary(jobTitle.getMinSalary());
        jobDTO.setReviews(reviews);
        return jobDTO;

    }
}
