package com.finances.controllers;

import com.finances.services.StockService;
import com.finances.utils.ScheduledUpdate;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ErrorController {
    public static final String ERROR_PATH = "/error";
    public static final String ERROR_MESSAGE_DOWNLOAD = "The stock does not exists, failed to download it";

    private final StockService stockService;
    private final ScheduledUpdate scheduledUpdate;

    public IndexController(StockService stockService, ScheduledUpdate scheduledUpdate) {
        this.stockService = stockService;
        this.scheduledUpdate = scheduledUpdate;
    }

    @RequestMapping(path = "/")
    String index(Model model){
        model.addAttribute("stocks",stockService.getAllStocks());
        model.addAttribute("isTriggerEnabled", scheduledUpdate.isTriggerEnabled());
        return "index";
    }


    @RequestMapping(ERROR_PATH)
    String error(Model model) {
        model.addAttribute("errorMessage", ERROR_MESSAGE_DOWNLOAD);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}