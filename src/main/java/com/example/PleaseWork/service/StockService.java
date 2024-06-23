package com.example.PleaseWork.service;


import com.example.PleaseWork.modal.StockData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {
    public List<StockData> GetStockData(String symbol);
}
