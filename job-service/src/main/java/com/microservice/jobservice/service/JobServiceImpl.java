package com.microservice.jobservice.service;


import com.microservice.jobservice.dto.JobDTO;
import com.microservice.jobservice.external.Company;
import com.microservice.jobservice.external.Review;
import com.microservice.jobservice.clients.CompanyClient;
import com.microservice.jobservice.clients.ReviewClient;
import com.microservice.jobservice.mapper.JobMapper;
import com.microservice.jobservice.modal.JobTitle;
import com.microservice.jobservice.repository.JobRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;
    private final JobRepo jobRepo;

    @Override
    @CircuitBreaker(name="companyService",
                fallbackMethod = "companyServiceFallback")
    public List<JobDTO> findAll() {

        List<JobTitle> jobTitles = jobRepo.findAll();
        return jobTitles.stream().map(this::getJobDTO).toList();
    }

    public List<String> companyServiceFallback(Exception e) {
        List<String> list = new ArrayList<>();
        list.add("companyServiceFallback");
        return list;
    }

    @Override
    public JobTitle createJob(JobTitle job) {
        return jobRepo.save(job);
    }

    @Override
    public JobDTO findById(Long id) {
        if(id!=null) {
            JobTitle jobTitles = jobRepo.findById(id).orElse(null);
            return jobTitles == null ? null : getJobDTO(jobTitles);
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {

        if (!jobRepo.existsById(id)) {
            return false;
        }

        jobRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean updateJob(Long id, JobTitle job) {

        jobRepo.save(job);

        JobTitle findJob = jobRepo.findById(id).orElse(null);
        if (findJob == null) {
            return false;
        }
        findJob.setTitle(job.getTitle());
        findJob.setDescription(job.getDescription());
        findJob.setLocation(job.getLocation());
        findJob.setMinSalary(job.getMinSalary());
        findJob.setMaxSalary(job.getMaxSalary());

        jobRepo.save(findJob);

        return true;
    }

    private JobDTO getJobDTO(JobTitle jobTitle) {
        Company company = companyClient.getCompanyById(jobTitle.getCompany_id());

        if (company == null) {
            return JobMapper.mapToJobDTO(jobTitle, null, List.of());
        }

        List<Review> reviewList = reviewClient.getReviewsByCompany(company.getId());
        if (reviewList == null) {
            reviewList = List.of();
        }
        System.out.println("reviewList:"+reviewList);
        return JobMapper.mapToJobDTO(jobTitle, company,reviewList);
    }

}
