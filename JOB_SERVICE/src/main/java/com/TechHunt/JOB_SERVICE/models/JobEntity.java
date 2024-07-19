package com.TechHunt.JOB_SERVICE.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class JobEntity {

    @Id
    String id;

    String title;

    String job_title;

    String description;

    String created_by;

    LocalDateTime created_at;

    List<Application> applications;
}
