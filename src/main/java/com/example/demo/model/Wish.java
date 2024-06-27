package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Long userId;
    private String symbol;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wish(Long userId, String symbol, int price) {
        this.userId = userId;
        this.symbol = symbol;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Wish(String symbol) {
        this.symbol = symbol;
    }
    public Wish() {}

    public Wish(String symbol, Long userId) {
        this.symbol = symbol;
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }



    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
