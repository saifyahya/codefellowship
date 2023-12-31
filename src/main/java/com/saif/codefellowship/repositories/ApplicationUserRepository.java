package com.saif.codefellowship.repositories;

import com.saif.codefellowship.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
