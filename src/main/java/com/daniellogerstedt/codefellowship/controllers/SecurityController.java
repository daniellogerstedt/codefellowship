package com.daniellogerstedt.codefellowship.controllers;

import com.daniellogerstedt.codefellowship.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.daniellogerstedt.codefellowship.repositories.ApplicationUserRepository;

@Controller
public class SecurityController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ApplicationUserRepository userRepo;

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String signup() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> I'M HERE");
        return "signup";

    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public RedirectView createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth,
            @RequestParam String bio) {
        // Hash incoming password immediately so that it is never stored in memory unhashed.
        String hashword = bCryptPasswordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username, hashword, firstName, lastName, dateOfBirth, bio);
        userRepo.save(newUser);
        return new RedirectView("/users");
    }

}