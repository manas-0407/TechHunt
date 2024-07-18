package com.TechHunt.HACKATHON_SERVICE.controller;

import com.TechHunt.HACKATHON_SERVICE.model.HackathonDto;
import com.TechHunt.HACKATHON_SERVICE.model.HackathonEntity;
import com.TechHunt.HACKATHON_SERVICE.model.RegisterDto;
import com.TechHunt.HACKATHON_SERVICE.service.HackService;
import com.TechHunt.HACKATHON_SERVICE.utility.Id_Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

@RestController
@RequestMapping(value = "/hackathon")
public class Routes {

    @Autowired
    HackService hackService;

    @GetMapping(value = "/get")
    public String get(){
        return "Hello manas";
    }

    @PostMapping(value = "/add")
    public String add_hackathon(@RequestBody HackathonDto hackathonDto){
        System.err.println("add route");

        return hackService.save(hackathonDto);

    }

    @GetMapping(value = "/upcoming")
    public ResponseEntity<List<HackathonEntity>> getUpcoming(){
        List<HackathonEntity> list = hackService.getAfterNow();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam String id , @RequestBody RegisterDto registerDto){
        return hackService.register(registerDto , id);
    }

    @GetMapping(value = "/excel")
    public ResponseEntity<Resource> downloadExcel(@RequestParam String id) throws IOException {

        String filename = id+".xlsx";
        ByteArrayInputStream data = hackService.getExcel(id);
        InputStreamResource file = new InputStreamResource(data);

        ResponseEntity<Resource> response = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION , "attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);

        return response;
    }

}
