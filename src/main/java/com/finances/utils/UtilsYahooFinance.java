package com.finances.utils;

import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

@Component
public class UtilsYahooFinance {

    /**
     * This function returns a Stock from Yahoo finance
     * @param symbol the symbol of stock
     * @return
     * @throws IOException
     */
    public Stock getStockFromYahooFinance(String symbol) throws IOException {
        return YahooFinance.get(symbol);
    }
}
