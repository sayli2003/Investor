package com.example.SelfInvest.repository;

import com.example.SelfInvest.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
