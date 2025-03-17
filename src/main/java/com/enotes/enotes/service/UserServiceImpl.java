package com.enotes.enotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.enotes.enotes.entity.User;
import com.enotes.enotes.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private User newUser;

    @Override
    public User saveUser(User user) {

        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public boolean existsEmailCheck(String email) {
        return userRepository.existsByEmail(email);
    }

    public void removeSessionMessage()
    {
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        session.removeAttribute("msg");
    }
    
}
