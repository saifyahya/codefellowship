package com.saif.codefellowship.controllers;

import com.saif.codefellowship.models.ApplicationUser;
import com.saif.codefellowship.repositories.ApplicationUserRepository;
import com.saif.codefellowship.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
    public RedirectView signUp(String firstName, String lastName, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth, String bio, String username, String password, RedirectAttributes redir){
        ApplicationUser checkUsername = applicationUserRepository.findByUsername(username);
        if(firstName.equals("")|| lastName.equals("") || username.equals("") || password.equals("")){
            redir.addFlashAttribute("errorMessage","Please fill all fields");
            return new RedirectView("/signup");
        }
        if(checkUsername!=null){
            redir.addFlashAttribute("errorMessage","Username is already used, try another one");
            System.out.println("Username already used");
            return new RedirectView("/signup");
        }

        try{
        String encryptedPassword = passwordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username,encryptedPassword,firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);     //login the user immediately after signup
        return new RedirectView("/");
    }   catch (Exception e) {
        // Handle parsing or other exceptions
        System.err.println("Error during signup: " + e.getMessage());
        return new RedirectView("/signup"); // Redirect back to signup page on error
    }
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
            List<ApplicationUser> users = applicationUserRepository.findAll();
            m.addAttribute("username", username);
          //  m.addAttribute("bio", user.getBio());
            m.addAttribute("posts",user.getPosts());
            m.addAttribute("allUsers",users);

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
    @GetMapping("/profile/{id}")
    public String getOthersProfile(@PathVariable Long id,  Model m){
            ApplicationUser user= applicationUserRepository.findById(id).get();
            if(user==null){
                throw new RuntimeException("User Not Found");
            }
            m.addAttribute("user",user);

            return "others-profile.html";

    }

}
