package com.saif.codefellowship.controllers;

import com.saif.codefellowship.models.ApplicationUser;
import com.saif.codefellowship.models.Post;
import com.saif.codefellowship.repositories.ApplicationUserRepository;
import com.saif.codefellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class PostsController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;
@PostMapping("/add-post")
    public RedirectView addPost(Principal p, String body){
    if(p != null){   // check if the user is authenticated (logged in)
        String username = p.getName();
        ApplicationUser user= applicationUserRepository.findByUsername(username);
        LocalDate currentDate= LocalDate.now();
        Post newPost = new Post(body,currentDate,user);
        postRepository.save(newPost);
    }
    return new RedirectView("/");
}
}
