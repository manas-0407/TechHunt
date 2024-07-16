package com.TechHunt.USER_SERVICE.repository;

import com.TechHunt.USER_SERVICE.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {

    UserEntity findByEmail(String email);
}
