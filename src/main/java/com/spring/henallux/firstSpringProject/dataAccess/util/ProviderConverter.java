package com.spring.henallux.firstSpringProject.dataAccess.util;

import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter {

    public static UserEntity userModelToUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setAge(user.getAge());
        userEntity.setMale(user.getMale());
        userEntity.setHobby(user.getHobby());
        return userEntity;
    }

    public static User userEntityToUserModel(UserEntity userEntity){
        User user = new User();
        user.setName(userEntity.getName());
        user.setAge(userEntity.getAge());
        user.setMale(userEntity.getMale());
        user.setHobby(userEntity.getHobby());
        return user;
    }
}
