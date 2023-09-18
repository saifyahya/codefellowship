package com.saif.codefellowship.services;

import com.saif.codefellowship.models.ApplicationUser;
import com.saif.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);  //return type is UserDetails
        if(applicationUser==null)
        throw new UsernameNotFoundException("Username"+username+"Not found");
        else
            return applicationUser;
    }
}
