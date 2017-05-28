package com.finances.domain;

import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

@Entity
public class StockEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String symbol;
    private String name;
    private String currency;
    private String stockExchange;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STOCK_QUOTE_ID")
    private StockQuoteEntity stockQuoteEntity;

    public StockEntity() {
    }

    public StockEntity(final Stock stock) {
        this.symbol = stock.getSymbol();
        this.currency = stock.getCurrency();
        this.name = stock.getName();
        this.stockExchange = stock.getStockExchange();
        this.stockQuoteEntity = new StockQuoteEntity(stock.getQuote());
//        this.quote = stock.getQuote();
//        this.stats = stock.getStats();
//        this.dividend = stock.getDividend();
    }

    public Integer getId() {
        return id;
    }

    public StockQuoteEntity getStockQuoteEntity() {
        return stockQuoteEntity;
    }
    
    
    public String getSymbol() {
        return symbol;
    }

        
    public String getName() {
        return name;
    }


    public String getStockExchange() {
        return stockExchange;
    }


    public String getCurrency() {
        return currency;
    }



    /*@OneToOne
    public StockQuote getQuote() {
        return quote;
    }


    @OneToOne
    public StockStats getStats() {
        return stats;
    }

    
    
    @OneToOne
    public StockDividend getDividend() {
        return dividend;
    }*/
}
