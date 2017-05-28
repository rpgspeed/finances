package com.finances.controllers;

import com.finances.repositories.StockRepository;
import com.finances.services.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yahoofinance.Stock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    private final StockService stockService;

    public IndexController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(path = "/")
    String index(Model model){
        model.addAttribute("stocks",stockService.getAllStocks());
        return "index";
    }
}