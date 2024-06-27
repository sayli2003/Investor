package com.example.demo.service;

import com.example.demo.model.OrderEntity;
//import com.example.demo.model.User;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String buyStock(Long userId, String stockName, Double price, Integer quantity) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Double totalCost = price * quantity;
        if (user.getBalance() < totalCost) {
            return "Insufficient balance to buy stock";
        }
        user.setBalance(user.getBalance() - totalCost);
        userRepository.save(user);

        OrderEntity order = new OrderEntity(userId, stockName, price, quantity);
        orderRepository.save(order);

        return "Stock bought successfully";
    }

    @Transactional
    public String sellStock(Long userId, String stockName, Double price, Integer quantity) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderEntity> orders = orderRepository.findByUserIdAndStockName(userId, stockName);
        int totalStockQuantity = orders.stream().mapToInt(OrderEntity::getQuantity).sum();

        if (totalStockQuantity < quantity) {
            return "Insufficient stock quantity to sell";
        }

        Double totalGain = price * quantity;
        user.setBalance(user.getBalance() + totalGain);
        userRepository.save(user);

        int remainingQuantity = quantity;
        for (OrderEntity order : orders) {
            if (order.getQuantity() <= remainingQuantity) {
                remainingQuantity -= order.getQuantity();
                orderRepository.delete(order);
            } else {
                order.setQuantity(order.getQuantity() - remainingQuantity);
                orderRepository.save(order);
                break;
            }
        }

        return "Stock sold successfully";
    }

    public List<OrderEntity> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}