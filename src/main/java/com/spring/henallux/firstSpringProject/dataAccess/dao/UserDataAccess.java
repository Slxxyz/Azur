package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.User;

import java.util.ArrayList;

public interface UserDataAccess {

    void save(User user);

    // Méthode pour récupérer les enfants selon l'âge et le hobby
    ArrayList<User> getChildByAgeAndHobby(Integer ageMin, Integer ageMax, String hobby);

    // Méthode pour récupérer les enfants selon une liste de hobbies
    ArrayList<User> getChildByHobbies(ArrayList<String> hobbies);
}
