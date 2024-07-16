package com.TechHunt.USER_SERVICE.service;

import com.TechHunt.USER_SERVICE.models.UserEntity;
import com.TechHunt.USER_SERVICE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean saveUser(UserEntity userEntity){
        try{
            userRepository.save(userEntity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UserEntity findbyemail(String email){
        return userRepository.findByEmail(email);
    }
}
