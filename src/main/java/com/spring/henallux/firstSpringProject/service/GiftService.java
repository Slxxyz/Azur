package com.spring.henallux.firstSpringProject.service;

import org.springframework.stereotype.Service;

@Service
public class GiftService {
    public String chooseGift(String hobby, int age){
        if(age < 5){
            return "Un puzzle concernant le thème du " + hobby;
        } else if (age < 10) {
            return "Un DVD concernant le thème du " + hobby;
        }else{
            return "Un livre concernant le thème du " + hobby;
        }
    }
}
