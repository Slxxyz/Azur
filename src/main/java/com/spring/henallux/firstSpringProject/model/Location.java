package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Location {

    private Integer locationId;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String country;

    @NotNull
    private Integer houseNumber;

    @Size(max = 10)
    private String letterBox;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String location;

    @NotNull
    private Integer postalCode;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String street;

    // Getters and setters

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getLetterBox() {
        return letterBox;
    }

    public void setLetterBox(String letterBox) {
        this.letterBox = letterBox;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
