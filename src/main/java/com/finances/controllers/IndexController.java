package com.finances.controllers;

import com.finances.services.StockService;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ErrorController {
    public static final String ERROR_PATH = "/error";
    private final StockService stockService;

    public IndexController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(path = "/")
    String index(Model model){
        model.addAttribute("stocks",stockService.getAllStocks());
        return "index";
    }


    @RequestMapping(ERROR_PATH)
    String error(Model model) {
        model.addAttribute("errorMessage", "HA FALLADO");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}