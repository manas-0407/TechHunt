package com.TechHunt.USER_SERVICE.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user" , uniqueConstraints = {@jakarta.persistence.UniqueConstraint(columnNames = "email")})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(unique = true , nullable = false)
    String email;

    @Column(nullable = false)
    String workType;
}

/*
{
    "username":"manas",
    "password":"manas",
    "email":"manas@gmail.com",
    "workType":"student"
}
 */