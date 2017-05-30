package com.finances.utils;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

/**
 * Created by Rpg on 30/05/2017.
 */
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
