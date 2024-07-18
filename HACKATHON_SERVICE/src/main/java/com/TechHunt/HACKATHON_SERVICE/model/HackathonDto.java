package com.TechHunt.HACKATHON_SERVICE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HackathonDto {

    @NonNull
    String title;

    @NonNull
    String about;

    @NonNull
    LocalDateTime start;

    @NonNull
    LocalDateTime end;
}
