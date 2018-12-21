package com.daniellogerstedt.codefellowship.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {

    // Database Provided
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    // User Provided
    public String img;
    @Column(unique = true)
    public String username;
    private String password;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String bio;
    public String limited;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="User_Follows",
        joinColumns = { @JoinColumn (name="follower_id")},
        inverseJoinColumns = { @JoinColumn(name="followee_id")})
    public Set<ApplicationUser> usersIFollow = new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
    public Set<ApplicationUser> followedByUsers = new HashSet<>();

    // FetchType.EAGER was the solution to an error I got. It was found at https://stackoverflow.com/questions/11746499/how-to-solve-the-failed-to-lazily-initialize-a-collection-of-role-hibernate-ex
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "user")
    public Set<BlogPost> posts = new HashSet<>();

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
        return "User: " + username + "\nFull Name: " + firstName + " " + lastName + "\nDate of Birth: " + dateOfBirth + "\n" + "Bio: " + bio;
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

    public Set<BlogPost> getPosts() {
        return this.posts;
    }

    public long getId() {
        return this.id;
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
