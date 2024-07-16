package com.TechHunt.USER_SERVICE.security;

import com.TechHunt.USER_SERVICE.models.CustomUserDetail;
import com.TechHunt.USER_SERVICE.models.UserEntity;
import com.TechHunt.USER_SERVICE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            throw new UsernameNotFoundException("User not found with email");
        }

        return new CustomUserDetail(email , userEntity.getPassword() , userEntity.getUsername() , new ArrayList<>());
    }
}
