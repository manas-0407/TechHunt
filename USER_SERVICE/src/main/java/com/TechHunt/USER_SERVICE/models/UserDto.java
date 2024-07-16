package com.TechHunt.USER_SERVICE.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    String username;
    String password;
    String email;
    String workType;

    // getters and setters
}


