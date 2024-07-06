package com.example.demo.controller;

import com.example.demo.model.SearchForm;
//import com.example.demo.model.User;
import com.example.demo.model.UserEntity;
import com.example.demo.service.Logger;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    @GetMapping("/")
    public String showLoginPage(UserEntity user, Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(UserEntity user,Model model) {
//        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registering")
    public String registerUser(UserEntity user, BindingResult result, Model model) {
        try{
            if (result.hasErrors()) {
                return "redirect:/register";
            }
            userService.registerUser(user);
            UserEntity loggeduser = userService.loginUser(user.getUserId(), user.getPassword());
            logger.setUser(loggeduser);
            model.addAttribute("message", "User registered successfully!");
            return "redirect:/";
        }
        catch(Exception e){
            model.addAttribute("message","Email already registered");
            return "redirect:/register";
        }

    }

    @PostMapping("/login")
    public String loginUser(UserEntity user, Model model) {
        UserEntity loggeduser = userService.loginUser(user.getUserId(), user.getPassword());
        if (loggeduser != null) {
            model.addAttribute("user", loggeduser);
            logger.setUser(loggeduser);
            return "redirect:/api/getchart";
        } else {
            model.addAttribute("message", "Invalid credentials. Please try again.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String Logout(Model model) {
        logger.setUser(new UserEntity());
        return "redirect:/";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        return "redirect:/api/getchart";
    }
}