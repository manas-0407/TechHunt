package com.TechHunt.JOB_SERVICE.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    String name;
    String email;
    Long phone;
    String location;
    String resume_name;
    Binary resume;

}
