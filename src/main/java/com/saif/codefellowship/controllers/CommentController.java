package com.saif.codefellowship.controllers;

import com.saif.codefellowship.models.ApplicationUser;
import com.saif.codefellowship.models.Comments;
import com.saif.codefellowship.models.Post;
import com.saif.codefellowship.repositories.ApplicationUserRepository;
import com.saif.codefellowship.repositories.CommentsRepository;
import com.saif.codefellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
     PostRepository postRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/add-comment/{postId}")
    public RedirectView addComment(@PathVariable Long postId,String text){
        Post currentPost = postRepository.findById(postId).get();
        Comments newComment = new Comments(text,currentPost);
        commentsRepository.save(newComment);
        return new RedirectView("/feed");
    }
    @PostMapping("/delete-comment/{commentId}/{postId}")
    public RedirectView deleteComment(Principal p, @PathVariable Long commentId, @PathVariable Long postId, Model m) {
        String currentUserName = p.getName();
        ApplicationUser currentUser = applicationUserRepository.findByUsername(currentUserName);
        List<Post> currentUserPosts= currentUser.getPosts();
        Comments commentRemove= commentsRepository.findById(commentId).get();
    boolean isMyPost = currentUserPosts.stream().anyMatch(post -> post.getId()==postId);
        if(isMyPost){  // each user can remove the comments on his posts only
            commentsRepository.deleteById(commentId);
        }

        return new RedirectView("/");
    }


}
