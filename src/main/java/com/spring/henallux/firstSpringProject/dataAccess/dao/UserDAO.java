package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.UserRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDAO implements UserDataAccess {

    private final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        ProviderConverter providerConverter = new ProviderConverter();
        UserEntity userEntity = providerConverter.userModelToUserEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public ArrayList<User> getChildByAgeAndHobby(Integer ageMin, Integer ageMax, String hobby) {
        List<UserEntity> userEntities = userRepository.findByAgeBetweenAndHobby(ageMin, ageMax, hobby);
        ArrayList<User> users = new ArrayList<>();
        for (UserEntity entity : userEntities) {
            users.add(ProviderConverter.userEntityToUserModel(entity));
        }
        return users;
    }

    @Override
    public ArrayList<User> getChildByHobbies(ArrayList<String> hobbies) {
        List<UserEntity> userEntities = userRepository.findByHobbyInOrderByName(hobbies);
        ArrayList<User> users = new ArrayList<>();
        for (UserEntity entity : userEntities) {
            users.add(ProviderConverter.userEntityToUserModel(entity));
        }
        return users;
    }
}
