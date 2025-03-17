package com.enotes.enotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enotes.enotes.entity.User;
import com.enotes.enotes.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexPage()
    {
        return "index";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user,HttpSession session)
    {


        //// TO CHECK IF EMAIL ADRESS ALREADY EXIST


        boolean f = userService.existsEmailCheck(user.getEmail());
        if (f) {
            session.setAttribute("msg", "Email Adress is Already Used");
        }else
        {
            User saveUser = userService.saveUser(user);
            if(saveUser != null)
            {
                //ERROR MESSAGE
                session.setAttribute("msg", "Register Successfull");
            }else
            {
                session.setAttribute("msg", "Something went Wrong on Server");
            }
        }
        
        return "redirect:/register";
    }


    @GetMapping("/signin")
    public String login()
    {
        return "login";
    }

    
}
