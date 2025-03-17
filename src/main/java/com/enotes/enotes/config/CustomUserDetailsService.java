package com.enotes.enotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enotes.enotes.entity.User;
import com.enotes.enotes.repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{

    // to check the database if that email or password are present

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null) {
            throw new UsernameNotFoundException("Invalid Details");
        }else{
            return new CustomUser(user);
        }

        
    }
    
}
