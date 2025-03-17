package com.enotes.enotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enotes.enotes.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    public boolean existsByEmail(String email);

    //  for the customuserdetails
    public User findByEmail(String email);
}
