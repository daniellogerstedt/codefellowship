package com.daniellogerstedt.codefellowship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daniellogerstedt.codefellowship.models.ApplicationUser;
import com.daniellogerstedt.codefellowship.repositories.ApplicationUserRepository;

import java.util.ArrayList;

@Controller
public class PageController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ApplicationUserRepository userRepo;

    @RequestMapping("/")
    public String index() {
        System.out.println(">>>> INDEX HIT <<<<");
        return "index";
    }

    @RequestMapping("/users")
    public String users(Model m) {
        ArrayList<String> limited = new ArrayList<>();
        Iterable<ApplicationUser> users = userRepo.findAll();
        for (ApplicationUser user : users) {
            limited.add(user.getLimited());
        }
        m.addAttribute("users", limited);
        return "users";
    }

    @RequestMapping("/users/{id}")
    public String users(@PathVariable long id, Model m) {
        m.addAttribute("users", userRepo.findById(id).get());
        return "users";
    }
}
