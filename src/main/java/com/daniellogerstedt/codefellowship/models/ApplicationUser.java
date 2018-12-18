package com.daniellogerstedt.codefellowship.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationUser {

    // Database Provided
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    // User Provided
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;
    private String limitedInfo;

    public ApplicationUser() {}

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.limitedInfo = "User: " + username + " First Name: " + firstName + " Date of Birth: " + dateOfBirth;
    }

    public String toString() {
        return "User: " + username + " Full Name: " + firstName + " " + lastName + " Date of Birth: " + dateOfBirth + "\n" + "Bio: " + bio;
    }

    public String getLimited () {
        return this.limitedInfo;
    }

}
