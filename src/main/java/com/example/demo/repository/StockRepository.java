package com.example.demo.repository;

import com.example.demo.model.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<StockData,Integer> {
    @Query("SELECT e.c FROM StockData e ORDER BY e.x")
    List<String> findAllValuesInColumnC();

    @Query("SELECT e.x FROM StockData e ORDER BY e.x")
    List<String> findAllValuesInColumnX();

}
