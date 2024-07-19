package com.TechHunt.JOB_SERVICE.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

    @NonNull
    String title;

    @NonNull
    String job_title;

    @NonNull
    String description;

}

