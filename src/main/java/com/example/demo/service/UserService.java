package com.example.demo.service;

//import com.example.demo.model.User;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity loginUser(Long userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }
}