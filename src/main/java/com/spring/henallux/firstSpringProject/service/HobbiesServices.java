package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.model.Hobby;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HobbiesServices {
    private ArrayList<Hobby> hobbies;

    public HobbiesServices() {
        hobbies = new ArrayList<>();
        Hobby hobby1 = new Hobby();
        hobby1.setId("1");
        hobby1.setName("Sport");
        hobbies.add(hobby1);

        Hobby hobby2 = new Hobby();
        hobby2.setId("2");
        hobby2.setName("Cinema");
        hobbies.add(hobby2);

        Hobby hobby3 = new Hobby();
        hobby3.setId("3");
        hobby3.setName("Reading");
        hobbies.add(hobby3);

        Hobby hobby4 = new Hobby();
        hobby4.setId("4");
        hobby4.setName("Music");
        hobbies.add(hobby4);
    }

    public ArrayList<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
