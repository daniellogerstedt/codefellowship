package com.daniellogerstedt.codefellowship.controllers;

import com.daniellogerstedt.codefellowship.models.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daniellogerstedt.codefellowship.models.ApplicationUser;
import com.daniellogerstedt.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ApplicationUserRepository userRepo;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/users")
    public String users(Model m) {
        m.addAttribute("users", userRepo.findAll());
        return "users";
    }

    @RequestMapping("/users/{id}")
    public String users(@PathVariable long id, Model m) {
        Optional<ApplicationUser> user = userRepo.findById(id);
        if (user.isPresent()) {
            m.addAttribute("user", user.get());
            m.addAttribute("myProfile", false);
            return "userProfile";
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping("/myprofile")
    public String userProfile(Model m, Principal p) {

        m.addAttribute("user", ((UsernamePasswordAuthenticationToken) p).getPrincipal());
        m.addAttribute("myProfile", true);
        return "userProfile";
    }

    @RequestMapping(value="/myprofile/newpost", method= RequestMethod.POST)
    public RedirectView blogPost(@RequestParam String blogPost, Principal p) {
        return new RedirectView("/myprofile");
    }
}
