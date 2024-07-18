package com.TechHunt.HACKATHON_SERVICE.model;

import lombok.Data;

import java.util.*;
import java.io.*;


@Data
public class RegisterDto {

    String full_name;
    String email;
    Long phone;
    String address;
    String skills;
    String link;

}

/*

{

    "title":"TEst title",
    "about":"nothing",
    "start":"2024-07-21T13:00:00",
    "end":"2024-07-22T13:00:00"
}

 */
