package com.example.PleaseWork.controller;

import com.example.PleaseWork.repository.NewsRepository;
import com.example.PleaseWork.repository.StockRepository;
import com.example.PleaseWork.service.NewsService;
import com.example.PleaseWork.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {
    @Autowired
    StockService stockService;
    @Autowired
    StockRepository stockRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsService newsService;

    @GetMapping("/chart")
    public String showUserList(Model model) {
        model.addAttribute("user", stockRepository.findAll());
        model.addAttribute("symbol", "AAPL");
        model.addAttribute("news", newsRepository.findAll());
        return "chart";
    }

    @GetMapping("/api/getchart")
    public String showStock(Model model) {
        stockService.GetStockData("IBM");
        newsService.getNews("AAPL");
        return "redirect:/chart";
    }
    @GetMapping("/trial")
    public String trial(Model model) {
        model.addAttribute("user", stockRepository.findAll());
        return "trial";
    }
//    @GetMapping("/showlist")
//    public String showListStock(Model model) {
//
//        model.addAttribute("users", stockRepository.findAll());
//        return "index";
//    }
    @GetMapping("/JapaneseChart")
    public String ShowJapaneseChart(Model model) {
        return "japaneseChart";
    }

    // additional CRUD methods
}