package com.spring.henallux.firstSpringProject.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

public class Customer implements UserDetails {

    @NotEmpty(message = "Le nom d'utilisateur ne peut pas être vide.")
    @Size(min = 3, max = 45, message = "Le nom d'utilisateur doit contenir entre 3 et 45 caractères.")
    private String username;

    @NotEmpty(message = "Le prénom ne peut pas être vide.")
    @Size(min = 3, max = 45, message = "Le prénom doit contenir entre 3 et 45 caractères.")
    private String firstName;

    @NotEmpty(message = "Le nom de famille ne peut pas être vide.")
    @Size(min = 3, max = 45, message = "Le nom de famille doit contenir entre 3 et 45 caractères.")
    private String lastName;

    @NotEmpty(message = "L'adresse email ne peut pas être vide.")
    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "L'adresse email doit être valide.")
    private String mailAddress;

    @NotEmpty(message = "Le mot de passe ne peut pas être vide.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Le mot de passe doit contenir au moins 8 caractères, avec des lettres majuscules, minuscules et un chiffre.")
    private String userPassword;

    @Pattern(regexp = "[0-9]*", message = "Le numéro de téléphone doit uniquement contenir des chiffres.")
    @Size(min = 7, max = 15, message = "Le numéro de téléphone doit contenir entre 7 et 15 chiffres.")
    private String telNumber;

    private Integer locationID;

    // Ajout de l'objet Location avec validation des champs
    @NotNull(message = "La localisation ne peut pas être vide.")
    private Location location;

    // Getters et Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // L'utilisateur n'est pas expiré par défaut
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // L'utilisateur n'est pas bloqué par défaut
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Les informations d'identification de l'utilisateur ne sont pas expirées par défaut
    }

    @Override
    public boolean isEnabled() {
        return true; // L'utilisateur est activé par défaut
    }

    // Ajout de la méthode toString pour afficher les informations de l'utilisateur
    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", locationID=" + locationID +
                ", location=" + location +
                '}';
    }
}