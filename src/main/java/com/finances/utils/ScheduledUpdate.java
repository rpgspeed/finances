package com.finances.utils;

import com.finances.services.StockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Component
public class ScheduledUpdate {

    private boolean triggerEnabled = true;
   
    private final StockService stockService;

    public ScheduledUpdate(StockService stockService) {
        this.stockService = stockService;
    }

    @Scheduled(initialDelay=1000, fixedRate=60000)
    public void updateLastOldStocksTrigger() {
        try {
            if (triggerEnabled) {
                stockService.updateLastOldStocks();
            }
        } catch (IOException e) {
            System.out.println("Failed automatic update");
        }

    }
    
    public void setTriggerEnabled(boolean triggerEnabled) {
        this.triggerEnabled = triggerEnabled;
    }

    public boolean isTriggerEnabled() {
        return triggerEnabled;
    }
}
