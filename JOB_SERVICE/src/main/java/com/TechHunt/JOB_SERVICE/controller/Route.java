package com.TechHunt.JOB_SERVICE.controller;

import com.TechHunt.JOB_SERVICE.models.ApplyDto;
import com.TechHunt.JOB_SERVICE.models.JobDto;
import com.TechHunt.JOB_SERVICE.models.JobEntity;
import com.TechHunt.JOB_SERVICE.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/job")
public class Route {

    @Autowired
    JobService service;

    @PostMapping( value = "/create")
    public ResponseEntity<String> job_create(@RequestBody JobDto jobDto){
        service.create(jobDto);
        return ResponseEntity.ok("Created");
    }

    @PostMapping( value = "/update")
    public ResponseEntity<String> update_job(@RequestParam String id,@RequestBody JobDto jobDto){
        String response = service.update(id,jobDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/apply")
    public ResponseEntity<String> apply(@RequestParam String id, @RequestParam ApplyDto applyDto) throws IOException {

        String response = service.apply_job(id , applyDto);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping(value = "/getAllJob")
    public List<JobEntity> getJob(){
        return service.getJob();
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<String> deleteJob(@RequestParam String id){
        String response = service.delete(id);

        return ResponseEntity.ok().body(response);
    }

}
