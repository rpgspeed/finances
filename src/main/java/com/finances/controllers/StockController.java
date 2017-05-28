package com.finances.controllers;

import com.finances.services.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "stock", method = RequestMethod.POST)
    String getStock(String symbol, Model model){
        try {
            stockService.getStock(symbol);
            return "redirect:/";

        } catch (IOException e) {
            return "error";
        }
    }

    @RequestMapping(value = "stock/delete/{id}", method = RequestMethod.GET)
    String deleteStock(@PathVariable Integer id, Model model){
        stockService.deleteStock(id);
        return "redirect:/";
    }

    @RequestMapping("/stocks")
    String getAllStocks(Model model) {
        model.addAttribute("stocks", stockService.getAllStocksFromAPI());
        return "stocks";
    }

}
