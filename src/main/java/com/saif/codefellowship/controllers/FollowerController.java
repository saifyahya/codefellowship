package com.saif.codefellowship.controllers;

import com.saif.codefellowship.models.ApplicationUser;
import com.saif.codefellowship.models.Follower;
import com.saif.codefellowship.models.Post;
import com.saif.codefellowship.repositories.ApplicationUserRepository;
import com.saif.codefellowship.repositories.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FollowerController {
    @Autowired
    FollowerRepository followerRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @PostMapping("/follow/{followedId}")
    public RedirectView newFollow(Principal p, @PathVariable Long followedId) {
        if (p != null) {   // Check if the user is authenticated (logged in)
            String username = p.getName();
            ApplicationUser followerUser = applicationUserRepository.findByUsername(username);
            ApplicationUser followedUser = applicationUserRepository.findById(followedId).orElse(null);

            if (followedUser != null) {
                // Check if the follow relationship already exists
                boolean isAlreadyFollowing = followerRepository.existsByFollowerAndFollowed(followerUser, followedUser);

                if (!isAlreadyFollowing) {
                    // Create a new follow relationship only if it doesn't already exist
                    Follower newConnection = new Follower(followerUser, followedUser);
                    followerRepository.save(newConnection);
                }
            }
        }
        return new RedirectView("/feed");
    }

    @GetMapping("/feed")
    public String getFeed(Principal p,Model m){
    String username = p.getName();
    ApplicationUser followerUser = applicationUserRepository.findByUsername(username);
    List<Follower> followedUsers = followerRepository.findByFollower(followerUser);

    List<List<Post>> postsByFollowedUsers = new ArrayList<>();
    List<Long> followedUserIds = new ArrayList<>();
        List<String> followedUserNames = new ArrayList<>();
    for (Follower follower : followedUsers) {
        ApplicationUser followedUser = follower.getFollowed();
        List<Post> followedUserPosts = followedUser.getPosts(); // Get the list of posts for the followed user
        postsByFollowedUsers.add(followedUserPosts);
        followedUserIds.add(followedUser.getId());
        followedUserNames.add(followedUser.getUsername());
    }
    // Add the list of lists of posts to the model
    m.addAttribute("postsByFollowedUsers", postsByFollowedUsers);
    m.addAttribute("followedUserIds", followedUserIds);
        m.addAttribute("followedUserNames", followedUserNames);

    return "feeds.html";
}
@PostMapping("/unfollow/{id}")
@Transactional
    public RedirectView unFollow(Principal p, @PathVariable Long id) {
        String currentUserName= p.getName();
        ApplicationUser currentUser = applicationUserRepository.findByUsername(currentUserName);
        ApplicationUser followedUser = applicationUserRepository.findById(id).get();
    followerRepository.deleteByFollowerAndFollowed(currentUser, followedUser);
return new RedirectView("/");

}
}
