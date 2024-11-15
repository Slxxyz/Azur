package com.spring.henallux.firstSpringProject.model;
import javax.validation.Valid;;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer age;

    private Boolean male;
    private String hobby;

    public User(){

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String toString() {
        return name + (male ? " il a " : " elle  a ") + age + " et aime " + hobby;
    }
}