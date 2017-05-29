package com.finances.utils;

import com.finances.services.StockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Rpg on 29/05/2017.
 */
@Component
public class ScheduledUpdate {

    private final StockService stockService;

    public ScheduledUpdate(StockService stockService) {
        this.stockService = stockService;
    }

    @Scheduled(initialDelay=1000, fixedRate=60000)
    public void reportCurrentTime() {
        try {
            stockService.updateLastOldStocks();
        } catch (IOException e) {
            System.out.println("Failed automatic update");
        }

    }
}
