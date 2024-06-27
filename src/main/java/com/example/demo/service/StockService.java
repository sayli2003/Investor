package com.example.demo.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {
    public void GetStockData(String symbol);
}
