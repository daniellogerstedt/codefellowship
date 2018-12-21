package com.daniellogerstedt.codefellowship.controllers;

import com.daniellogerstedt.codefellowship.models.BlogPost;
import com.daniellogerstedt.codefellowship.models.ResourceNotFoundException;
import com.daniellogerstedt.codefellowship.repositories.BlogPostRepository;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class PageController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ApplicationUserRepository userRepo;

    @Autowired
    BlogPostRepository blogRepo;

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
    public ModelAndView users(@PathVariable long id, Model m, Principal p) {
        Optional<ApplicationUser> user = userRepo.findById(id);
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        if (user.isPresent()) {
            m.addAttribute("user", user.get());
            if (currentUser.getId() != user.get().getId()) {
                m.addAttribute("myProfile", false);
            } else {
                return new ModelAndView("redirect:/myprofile");
            }
            return new ModelAndView("userProfile");
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value="/users/{id}/follow", method=RequestMethod.POST)
    public RedirectView followUser(Principal p, @PathVariable long id) {
        ApplicationUser user = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        if(userRepo.findById(id).isPresent()) {
            ApplicationUser followed = userRepo.findById(id).get();
            user.usersIFollow.add(followed);
            followed.followedByUsers.add(user);
            userRepo.save(followed);
            userRepo.save(user);
        }
        return new RedirectView("/users/" + id);
    }

    @RequestMapping("/myprofile")
    public String userProfile(Model m, Principal p) {
        ApplicationUser user = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        m.addAttribute("user", user);
        m.addAttribute("myProfile", true);
        return "userProfile";
    }

    @RequestMapping(value="/myprofile", method= RequestMethod.POST)
    public RedirectView blogPost(@RequestParam String content, Principal p) {
        ApplicationUser user = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        BlogPost post = new BlogPost(content, user);
        blogRepo.save(post);
        user.posts.add(post);
        userRepo.save(user);
        return new RedirectView("/myprofile");
    }

    @RequestMapping(value="/feed")
    public String getFeed(Model m, Principal p) {
        ApplicationUser user = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        Set<BlogPost> feedPosts = new HashSet<>();
        for (ApplicationUser friend : user.usersIFollow) {
            feedPosts.addAll(friend.posts);
        }
        m.addAttribute("posts", feedPosts);
        return "feed";
    }
}
