package com.example.demo.service;


import com.example.demo.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class Logger {
    private UserEntity user;
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }
}
