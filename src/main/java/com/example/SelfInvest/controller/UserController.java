package com.example.SelfInvest.controller;

import com.example.SelfInvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    // was already there didnt remove, showUserList() too

    @GetMapping("/index")
    public String showUserList(Model model) {
        return "index";
    }

    @GetMapping("/history")
    public String showUserHistory(Model model) {
        return "history";
    }
}