package com.saif.codefellowship.controllers;

import com.saif.codefellowship.models.ApplicationUser;
import com.saif.codefellowship.repositories.ApplicationUserRepository;
import com.saif.codefellowship.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage(){
        return"login.html";
    }
    @GetMapping("/signup")
    public String getSignupPage(){
        return"signup.html";
    }
    @PostMapping("/signup")
    public RedirectView signUp(String firstName, String lastName, String dateOfBirth, String bio, String username, String password){
        ApplicationUser checkUsername = applicationUserRepository.findByUsername(username);
        if(checkUsername!=null){
            System.out.println("Username already used");
            return new RedirectView("/signup");
        }
        String encryptedPassword = passwordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username,encryptedPassword,firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);     //login the user immediately after signup
        return new RedirectView("/");
    }
    public void authWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        }catch (ServletException e){
            e.printStackTrace();
        }
    }
    @GetMapping("/logout")
    public String getLogoutPage(){
        return "index.html";
    }
    @GetMapping("/")
    public String getHomePage(Principal p, Model m){
        if(p != null){   // check if the user is authenticated (logged in)
            String username = p.getName();
            ApplicationUser user= applicationUserRepository.findByUsername(username);
            m.addAttribute("username", username);
            m.addAttribute("bio", user.getBio());
            m.addAttribute("posts",user.getPosts());
        }
        return "index.html";
    }
    @GetMapping("/myprofile")
    public String getMyProfile(Principal p, Model m){
        if(p != null){   // check if the user is authenticated (logged in)
            String username = p.getName();
            ApplicationUser user= applicationUserRepository.findByUsername(username);
            m.addAttribute("user", user);

            return "user-profile.html";

        }
        return "index.html";
    }
}
