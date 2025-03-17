package com.enotes.enotes.service;

import com.enotes.enotes.entity.User;

public interface UserService {


    public User saveUser( User user);
    public boolean existsEmailCheck(String email);
    
    
}
