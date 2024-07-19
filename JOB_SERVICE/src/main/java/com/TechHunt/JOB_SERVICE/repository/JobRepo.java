package com.TechHunt.JOB_SERVICE.repository;

import com.TechHunt.JOB_SERVICE.models.JobEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends MongoRepository<JobEntity , String> {
}
