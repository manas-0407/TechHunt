package com.TechHunt.JOB_SERVICE.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ApplyDto {

    String name;
    String email;
    Long phone;
    String location;
    MultipartFile resume;

}
