package com.example.PleaseWork.repository;

import com.example.PleaseWork.modal.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News,Integer> {
}
