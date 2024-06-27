package com.example.demo.controller;

import com.example.demo.model.OrderEntity;
import com.example.demo.service.Logger;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Logger logger;

    @PostMapping("/buy")
    public ResponseEntity<?> buyStock(@RequestBody OrderRequest request) {
        String message = orderService.buyStock(logger.getUser().getUserId(), request.getStockName(), request.getPrice(), request.getQuantity());
        return ResponseEntity.ok(new ResponseMessage(message));
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellStock(@RequestBody OrderRequest request) {
        String message = orderService.sellStock(logger.getUser().getUserId(), request.getStockName(), request.getPrice(), request.getQuantity());
        return ResponseEntity.ok(new ResponseMessage(message));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderEntity>> getUserOrders(@PathVariable Long userId) {
        List<OrderEntity> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }
}

class OrderRequest {
    private String stockName;
    private Double price;
    private Integer quantity;

    // Getters and setters

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}