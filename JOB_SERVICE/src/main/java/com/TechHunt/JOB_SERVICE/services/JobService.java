package com.TechHunt.JOB_SERVICE.services;

import com.TechHunt.JOB_SERVICE.models.Application;
import com.TechHunt.JOB_SERVICE.models.ApplyDto;
import com.TechHunt.JOB_SERVICE.models.JobDto;
import com.TechHunt.JOB_SERVICE.models.JobEntity;
import com.TechHunt.JOB_SERVICE.repository.JobRepo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobService {

    @Autowired
    JobRepo jobRepo;

    public void create(JobDto dto){

        JobEntity entity = new JobEntity();

        String name = "manas";
        /*
        Get from security context
         */

        entity.setTitle(dto.getTitle());
        entity.setJob_title(dto.getJob_title());
        entity.setDescription(dto.getDescription());
        entity.setCreated_by(name);
        entity.setCreated_at(LocalDateTime.now());
        entity.setApplications(new ArrayList<>());

        jobRepo.save(entity);
    }

    public String apply_job(String id, ApplyDto dto) throws IOException {

        Optional<JobEntity> optional = jobRepo.findById(id);
        if(optional.isEmpty()) return "No Such Job";

        MultipartFile file = dto.getResume();
        String resume_name = file.getName();
        byte[] bytes = file.getBytes();

        Application application = new Application();
        application.setName(dto.getName());
        application.setLocation(dto.getLocation());
        application.setEmail(dto.getEmail());
        application.setPhone(dto.getPhone());
        application.setResume_name(resume_name);
        application.setResume(new Binary(BsonBinarySubType.BINARY
                , bytes));
        JobEntity job = optional.get();
        job.getApplications().add(application);

        jobRepo.save(job);

        return "Applied for Job";
    }

    public String update(String id , JobDto dto){
        String username = "manas";
        /*
        JWt
         */
        Optional<JobEntity> Optionaljob = jobRepo.findById(id);

        if(Optionaljob.isEmpty()) return "No such job";

        JobEntity job = Optionaljob.get();

        if(username.compareTo(job.getCreated_by()) == 0){
            job.setJob_title(dto.getTitle());
            job.setJob_title(dto.getJob_title());
            job.setDescription(dto.getDescription());
            return "Job Updated";
        }
        return "Can only be updated by creator";
    }

    public List<JobEntity> getJob(){
        return jobRepo.findAll();
    }

    public String delete(String id){

        String username = "manas";

        /*
        JWt
         */

        Optional<JobEntity> Optionaljob = jobRepo.findById(id);

        if(Optionaljob.isEmpty()) return "No such job";

        JobEntity job = Optionaljob.get();

        if(username.compareTo(job.getCreated_by()) == 0){
            jobRepo.deleteById(id);
            return "Job deleted";
        }
        return "Can only be deleted by creator";
    }
}
