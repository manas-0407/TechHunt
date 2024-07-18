package com.TechHunt.HACKATHON_SERVICE.service;

import com.TechHunt.HACKATHON_SERVICE.model.HackathonDto;
import com.TechHunt.HACKATHON_SERVICE.model.HackathonEntity;
import com.TechHunt.HACKATHON_SERVICE.model.RegisterDto;
import com.TechHunt.HACKATHON_SERVICE.repository.Hack_Repository;
import com.TechHunt.HACKATHON_SERVICE.utility.GetExcelData;
import com.TechHunt.HACKATHON_SERVICE.utility.Id_Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

@Service
public class HackService {


    @Autowired
    Id_Generator idGenerator;

    @Autowired
    Hack_Repository hackRepository;

    @Autowired
    GetExcelData excelData;

    public String save(HackathonDto hackathonDto){
        String creator_name="manas", creator_email="manas@gmail.com";
        /*
            Authenticaation auth = SecurityContextHolder.gteContext.getAuth
            name = getName;
            email = getEmail;
         */

        String id = idGenerator.generate_id(hackathonDto.getTitle());

        Optional<HackathonEntity> optionalHackathonEntity = hackRepository.findById(id);

        if(optionalHackathonEntity.isPresent()) return "Title already taken";

        HackathonEntity entity = new HackathonEntity();
        entity.setId(id);
        entity.setTitle(hackathonDto.getTitle());
        entity.setAbout(hackathonDto.getAbout());
        entity.setStart(hackathonDto.getStart());
        entity.setEnd(hackathonDto.getEnd());
        entity.setCreated_at(LocalDateTime.now());
        entity.setCreated_by(creator_name);
        entity.setCreated_by_email(creator_email);
        entity.setRegistrations(new ArrayList<>());
        entity.setRegisteredEmail(new TreeSet<>());

        hackRepository.save(entity);
        return "Saved Successful";
    }

    public List<HackathonEntity> getAfterNow(){
        List<HackathonEntity> list = hackRepository.findHackathonsStartingAfter(LocalDateTime.now());
        for(HackathonEntity hackathon : list){
            hackathon.setRegistrations(null);
            hackathon.setRegisteredEmail(null);
        }
        return list;
    }

    public String register(RegisterDto registerDto , String id){

        Optional<HackathonEntity> Optional_entity = hackRepository.findById(id);

        if(Optional_entity.isEmpty()) return "No such hackathon present";

        HackathonEntity entity = Optional_entity.get();

        if(entity.getRegisteredEmail().contains(registerDto.getEmail()))
            return "User already registered ";

        entity.getRegistrations().add(registerDto);
        entity.getRegisteredEmail().add(registerDto.getEmail());

        hackRepository.save(entity);
        return "Registered";
    }

    public ByteArrayInputStream getExcel(String id) throws IOException {

        Optional<HackathonEntity> Optional_entity = hackRepository.findById(id);

        if(Optional_entity.isEmpty()) return null;

        HackathonEntity entity = Optional_entity.get();

        return excelData.dataToExcel(entity.getRegistrations());
    }
}
