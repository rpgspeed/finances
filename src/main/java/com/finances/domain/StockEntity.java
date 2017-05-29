package com.finances.domain;

import org.hibernate.annotations.Type;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class StockEntity implements Serializable {
    public static final String SYSTEM_USER = "System";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String symbol;
    private String name;
    private String currency;
    private String stockExchange;

    private String createdBy;
    private String updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
        this.createdBy = SYSTEM_USER;
        this.createdAt = LocalDateTime.now();
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAudit() {
        updatedAt = LocalDateTime.now();
        updatedBy = SYSTEM_USER;
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
