package com.saif.codefellowship.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private LocalDate createdDate;
    @ManyToOne()
    private ApplicationUser user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comments> comments;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public Post(String body, LocalDate createdDate, ApplicationUser user) {
        this.body = body;
        this.createdDate = createdDate;
        this.user = user;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
