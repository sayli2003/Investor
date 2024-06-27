package com.example.demo.controller;

import com.example.demo.model.OrderEntity;
import com.example.demo.model.Wish;
import com.example.demo.repository.WishRepository;
import com.example.demo.service.Logger;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PortfolioController {
    @Autowired
    OrderService orderService;
    @Autowired
    WishRepository wishRepository;

    @Autowired
    private Logger logger;

    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        model.addAttribute("pe", orderService.getUserOrders(logger.getUser().getUserId()));
        List<OrderEntity> wishList =orderService.getUserOrders(logger.getUser().getUserId());
        for (OrderEntity wish : wishList) {
            System.out.println(wish.getId()+"    "+wish.getPrice()+"   "+wish.getQuantity()+"  "+wish.getStockName());
        }
        return "portfolio";
    }
    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        model.addAttribute("wishs", wishRepository.findByUserId(logger.getUser().getUserId()));
        List<Wish> wishList = wishRepository.findByUserId(logger.getUser().getUserId());
        for (Wish wish : wishList) {
            System.out.println(wish.getId()+"    "+wish.getPrice()+"   "+wish.getSymbol());
        }
        return "wishlist";
    }
//    @GetMapping("{symbol}/{userId}/AddtoWishlist")
//    public String addwish(@PathVariable Long userId,@PathVariable String symbol, Model model) {
//        wishRepository.save(new Wish(symbol, userId));
//        return "wishlist";
//    }

}
