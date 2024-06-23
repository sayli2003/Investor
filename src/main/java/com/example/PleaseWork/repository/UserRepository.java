package com.example.PleaseWork.repository;

import com.example.PleaseWork.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
