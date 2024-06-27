package com.example.demo.controller;

import com.example.demo.model.SearchForm;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.NewsService;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    String Symbol;

    @GetMapping("/chart")
    public String showUserList(SearchForm searchForm, Model model) {
        model.addAttribute("symbol", this.Symbol);
        model.addAttribute("news", newsRepository.findAll());
        model.addAttribute("labels", stockRepository.findAllValuesInColumnX());
        model.addAttribute("data", stockRepository.findAllValuesInColumnC());

        return "chart";
    }

    @PostMapping("/api/search")
    public String buyStock(SearchForm searchForm, BindingResult result, Model model) {
//        System.out.println(searchForm.getQuery());
        if (result.hasErrors()) {
            return "chart";
        }
        this.Symbol = searchForm.getQuery();
        return "redirect:/loadDb";
    }

    @GetMapping("/loadDb")
    public String dbLoader(Model model) {
        stockService.GetStockData(this.Symbol);
        newsService.getNews(this.Symbol);
        return "redirect:/chart";
    }

    @GetMapping("/api/getchart")
    public String showStock(Model model) {
        this.Symbol = "AAPL";
        return "redirect:/loadDb";
    }
    @GetMapping("/trial")
    public String trial(Model model) {
        model.addAttribute("user", stockRepository.findAll());
        return "trial";
    }
    // additional CRUD methods
}