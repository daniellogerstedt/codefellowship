package com.daniellogerstedt.codefellowship.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ApplicationUser implements UserDetails {

    // Database Provided
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    // User Provided
    private String img;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;
    private String limited;

    public ApplicationUser() {}

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.limited = "User: " + username + " First Name: " + firstName + " Date of Birth: " + dateOfBirth;
        this.img = "/assets/defaultprofile.jpg";
    }

    public String toString() {
        return "User: " + username + " Full Name: " + firstName + " " + lastName + " Date of Birth: " + dateOfBirth + "\n" + "Bio: " + bio;
    }

    public String getLimited () {
        return this.limited;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
