package com.finances.domain;

import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class StockQuoteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STOCK_QUOTE_ID")
    private Integer id;

    private BigDecimal previousClose;

    private BigDecimal price;

    public StockQuoteEntity() {
    }

    public StockQuoteEntity(final StockQuote stockQuote) {
        this.previousClose = stockQuote.getPreviousClose().setScale(4);
        this.price = stockQuote.getPrice().setScale(4);
    }

    public BigDecimal getPreviousClose() {
        return previousClose;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
