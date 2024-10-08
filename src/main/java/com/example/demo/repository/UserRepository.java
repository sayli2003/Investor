package com.example.demo.repository;

//import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserIdAndPassword(Long userId, String password);
    UserEntity findByUserId(Long userId);
}
