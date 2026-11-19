package com.microservice.jobservice.controller;


import com.microservice.jobservice.dto.JobDTO;
import com.microservice.jobservice.modal.JobTitle;
import com.microservice.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class JobController {

    private final JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody JobTitle job){
        JobTitle Obj = jobService.createJob(job);
       if(Obj != null){
           return ResponseEntity.status(HttpStatus.CREATED).body("Job added successfully");
       }else{
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Job could not be created");
       }

    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> findAll(@PathVariable Long id){
        JobDTO job = jobService.findById(id);
        if(job == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        if(!jobService.deleteJobById(id)){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("Job deleted successfully");
        }
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody JobTitle job){
        boolean update = jobService.updateJob(id,job);
        if(update){
            return ResponseEntity.status(HttpStatus.OK).body("Job updated successfully");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Job not found !!");
        }
    }
}
