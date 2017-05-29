package com.finances.services;

import com.finances.domain.StockEntity;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rpg on 23/05/2017.
 */
public interface StockService {


    Stock getStock(String symbol) throws IOException;

    List<Stock> getAllStocksFromAPI();

    Iterable<StockEntity> getAllStocks();

    void deleteStock(Integer id);

    void updateLastOldStocks() throws IOException;
}
