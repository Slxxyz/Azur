package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name = "magickey")
public class MagicKeyEntity {

    @Id
    @Column(name = "magicvalue")
    private String magicvalue;  // Utilisation de magicvalue comme clé primaire

    // Constructeurs, getters et setters

    public MagicKeyEntity() {}

    public String getMagicvalue() {
        return magicvalue;
    }

    public void setMagicvalue(String magicvalue) {
        this.magicvalue = magicvalue;
    }
}
