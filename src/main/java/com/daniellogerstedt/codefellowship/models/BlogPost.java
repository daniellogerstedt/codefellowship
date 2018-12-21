package com.daniellogerstedt.codefellowship.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    public Date postDateTime;
    public String contents;

    @ManyToOne
    @JoinColumn(name="application_user_id")
    public ApplicationUser user;

    public BlogPost () {}

    public BlogPost (String contents, ApplicationUser user) {
        this.contents = contents;
        this.postDateTime = new Date();
        this.user = user;
    }
}
