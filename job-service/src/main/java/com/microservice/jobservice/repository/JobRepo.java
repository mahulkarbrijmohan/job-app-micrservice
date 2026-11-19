package com.microservice.jobservice.repository;


import com.microservice.jobservice.modal.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<JobTitle, Long> {
}
