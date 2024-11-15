package com.spring.henallux.firstSpringProject.dataAccess.repository;

import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // Requête pour trouver les enfants avec un âge entre deux valeurs et un hobby spécifique
    List<UserEntity> findByAgeBetweenAndHobby(Integer ageMin, Integer ageMax, String hobby);

    // Requête pour trouver les enfants ayant l'un des hobbies donnés, triés par nom
    List<UserEntity> findByHobbyInOrderByName(ArrayList<String> hobbies);
}
