package com.finances.services.impl;


import com.finances.domain.StockEntity;
import com.finances.repositories.StockRepository;
import com.finances.services.StockService;
import com.finances.utils.UtilsCSV;
import com.finances.utils.UtilsYahooFinance;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class StockServiceImpl implements StockService{

    private final UtilsCSV utilsCSV;

    private final StockRepository stockRepository;

    private final UtilsYahooFinance utilsYahooFinance;

    public StockServiceImpl(UtilsCSV utilsCSV, StockRepository stockRepository, UtilsYahooFinance utilsYahooFinance) {
        this.utilsCSV = utilsCSV;
        this.stockRepository = stockRepository;
        this.utilsYahooFinance = utilsYahooFinance;
    }

    @Override
    public Stock getStock(final String symbol) throws IOException {
        Stock stock = utilsYahooFinance.getStockFromYahooFinance(symbol);
        StockEntity stockEntity = new StockEntity(stock);
        saveOrUpdate(stockEntity);
        return stock;
    }

    @Override
    public List<Stock> getAllStocksFromAPI() {
        List<Stock> stockList = new ArrayList<>();
        try {
            Set<String> allSymbols = utilsCSV.getAllSymbols("static/companylist.csv");
            if (allSymbols.size() > 10) {
                allSymbols = new HashSet<>(new ArrayList<String>(allSymbols).subList(0, 10));
            }

            for (String symbol : allSymbols) {
                stockList.add(utilsYahooFinance.getStockFromYahooFinance(symbol));
            }

            for (Stock stock : stockList) {
                StockEntity stockEntity = new StockEntity(stock);
                saveOrUpdate(stockEntity);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    private StockEntity saveOrUpdate(final StockEntity stockEntity) {
        StockEntity stockEntityToUpdate = stockRepository.findBySymbol(stockEntity.getSymbol());

        if (stockEntityToUpdate != null) {
            stockEntity.setId(stockEntityToUpdate.getId());
            stockEntity.setUpdateAudit();
        }
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

    @Override
    public void updateLastOldStocks() throws IOException {
        List<StockEntity> lastOldStocks = stockRepository.getLastOldStocks();
        List<StockEntity> stockEntities = lastOldStocks;
//        if (lastOldStocks.size() > 10) {
//            stockEntities = lastOldStocks.subList(0, 10);
//        }

        for (StockEntity lastOldStock : stockEntities) {
            Stock stock = utilsYahooFinance.getStockFromYahooFinance(lastOldStock.getSymbol());
            saveOrUpdate(new StockEntity(stock));
            System.out.println("TRIGGER: "+lastOldStock.getSymbol());
        }
    }

}
