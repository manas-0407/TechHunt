package com.TechHunt.USER_SERVICE.controller;

import com.TechHunt.USER_SERVICE.models.*;
import com.TechHunt.USER_SERVICE.security.JWTGenerator;
import com.TechHunt.USER_SERVICE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserRoute{

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    @PostMapping(value = "/register")
    public String register(@RequestBody UserDto userDto){

        UserEntity userEntity = new UserEntity();
        System.err.println(userDto.toString());

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setEmail(userDto.getEmail());
        userEntity.setWorkType(userDto.getWorkType());

        boolean status = userService.saveUser(userEntity);
        if(status) return "Registered Successfully";
        return "Invalid";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        try{

            System.err.println(loginDto.toString());

            UserEntity userEntity = userService.findbyemail(loginDto.getEmail());

            if(userEntity == null){
                throw new BadCredentialsException("Email Not Registered");
            }

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail() , loginDto.getPassword());

//            Map<String , Object> details = new HashMap<>();
//            details.put("username" , userEntity.getUsername());
//            token.setDetails(details);

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtGenerator.generateToken(authentication);
//            SecurityContextHolder.getContext().getAuthentication().getName();
            return ResponseEntity.ok(new AuthResponseDto(jwt));

        }catch (Exception e){
            AuthResponseDto authResponseDto = new AuthResponseDto(e.getClass() == BadCredentialsException.class ?
                    e.getMessage() : "BAD CREDENTIALS");
            authResponseDto.setTokenType("-");
            return ResponseEntity.badRequest().body(authResponseDto);

        }
    }

    @GetMapping(value = "/update")
    public String update(){
        // Username password email

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        return userDetail.getUsername();

    }


}
