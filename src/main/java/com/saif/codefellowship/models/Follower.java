package com.saif.codefellowship.models;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated identifier for the Follower entity

    @ManyToOne  // many followers can follow the same person
    @JoinColumn(name = "follower_id")  // to represent a foreign key
    private ApplicationUser follower; // User entity representing the follower

    @ManyToOne          // many followed persons can be associated to the same follower
    @JoinColumn(name = "followed_id") // to represent a foreign key
    private ApplicationUser followed; // User entity representing the user being followed

    // Constructors, getters, setters, etc.

    public Long getId() {
        return id;
    }

    public ApplicationUser getFollower() {
        return follower;
    }

    public void setFollower(ApplicationUser follower) {
        this.follower = follower;
    }

    public ApplicationUser getFollowed() {
        return followed;
    }

    public void setFollowed(ApplicationUser followed) {
        this.followed = followed;
    }

    public Follower() {
    }

    public Follower(ApplicationUser follower, ApplicationUser followed) {
        this.follower = follower;
        this.followed = followed;
    }
}

