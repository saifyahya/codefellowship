package com.saif.codefellowship.repositories;

import com.saif.codefellowship.models.ApplicationUser;
import com.saif.codefellowship.models.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findByFollower(ApplicationUser follower);
    boolean existsByFollowerAndFollowed(ApplicationUser follower, ApplicationUser followed); //check existing relation

}
