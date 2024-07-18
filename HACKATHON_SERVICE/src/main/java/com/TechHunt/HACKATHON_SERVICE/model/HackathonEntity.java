package com.TechHunt.HACKATHON_SERVICE.model;

import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HackathonEntity {

    @Id
    String id;

    @NonNull
    String title;

    @NonNull
    String about;

    @NonNull
    LocalDateTime start;

    @NonNull
    LocalDateTime end;

    String created_by;

    String created_by_email;

    LocalDateTime created_at;

    List<RegisterDto> registrations;

    TreeSet<String> registeredEmail;

}
