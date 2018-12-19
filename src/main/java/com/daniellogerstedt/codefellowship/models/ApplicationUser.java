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
    public long id;

    // User Provided
    public String img;
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String bio;
    public String limited;

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

}
