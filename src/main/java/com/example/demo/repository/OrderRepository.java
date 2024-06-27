package com.example.demo.repository;

import com.example.demo.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserIdAndStockName(Long userId, String stockName);
    List<OrderEntity> findByUserId(Long userId);
}