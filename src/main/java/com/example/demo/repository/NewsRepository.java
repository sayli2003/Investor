package com.example.demo.repository;


import com.example.demo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News,Integer> {
}
