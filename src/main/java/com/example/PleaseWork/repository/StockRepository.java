package com.example.PleaseWork.repository;

import com.example.PleaseWork.modal.StockData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockData,Integer> {
}
