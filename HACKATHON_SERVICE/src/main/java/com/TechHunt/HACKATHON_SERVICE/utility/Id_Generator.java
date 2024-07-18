package com.TechHunt.HACKATHON_SERVICE.utility;

import org.springframework.stereotype.Component;

import java.util.*;
import java.io.*;

@Component
public class Id_Generator {

    public String generate_id(String name){
        StringBuilder id = new StringBuilder(name.toLowerCase());
        for(int index = 0 ; index < id.length() ; index++){
            if(id.charAt(index) == ' '){
                id.setCharAt(index,'_');
            }
        }
        return String.valueOf(id);
    }

}