package com.microservice.jobservice.service;



import com.microservice.jobservice.dto.JobDTO;
import com.microservice.jobservice.modal.JobTitle;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    JobTitle createJob(JobTitle job);

    JobDTO findById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, JobTitle job);
}
