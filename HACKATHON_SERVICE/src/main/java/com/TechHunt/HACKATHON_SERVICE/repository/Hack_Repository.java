package com.TechHunt.HACKATHON_SERVICE.repository;

import com.TechHunt.HACKATHON_SERVICE.model.HackathonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.*;

public interface Hack_Repository extends MongoRepository<HackathonEntity , String> {

    @Query("{ 'start' : { $gt: ?0 } }")
    List<HackathonEntity> findHackathonsStartingAfter(LocalDateTime startTime);

}
