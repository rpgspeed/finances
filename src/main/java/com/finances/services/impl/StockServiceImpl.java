package com.finances.services.impl;


import com.finances.domain.StockEntity;
import com.finances.repositories.StockRepository;
import com.finances.services.StockService;
import com.finances.utils.UtilsCSV;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class StockServiceImpl implements StockService{

    private final UtilsCSV utilsCSV;

    private final StockRepository stockRepository;

    public StockServiceImpl(UtilsCSV utilsCSV, StockRepository stockRepository) {
        this.utilsCSV = utilsCSV;
        this.stockRepository = stockRepository;
    }

    public void aaa() throws IOException {
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
        Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
        Stock intel = stocks.get("INTC");
        Stock airbus = stocks.get("AIR.PA");
    }

    @Override
    public Stock getStock(final String symbol) throws IOException {
        Stock stock = YahooFinance.get(symbol);
        StockEntity stockEntity = new StockEntity(stock);
        save(stockEntity);
        return stock;
    }

    @Override
    public List<Stock> getAllStocksFromAPI() {
        List<Stock> stockList = new ArrayList<>();
        try {
            Set<String> allSymbols = utilsCSV.getAllSymbols("static/companylist.csv");
            allSymbols = new HashSet<>(new ArrayList<String>(allSymbols).subList(0, 10));
            for (String symbol : allSymbols) {
                stockList.add(YahooFinance.get(symbol));
            }

            for (Stock stock : stockList) {
                StockEntity stockEntity = new StockEntity(stock);
                save(stockEntity);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    public StockEntity save(final StockEntity stockEntity) {
        return stockRepository.save(stockEntity);
    }

    @Override
    public Iterable<StockEntity> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public void deleteStock(final Integer id){
        stockRepository.delete(id);
            }
}
