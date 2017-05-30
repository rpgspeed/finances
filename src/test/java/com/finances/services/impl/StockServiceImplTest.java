package com.finances.services.impl;

import com.finances.domain.StockEntity;
import com.finances.repositories.StockRepository;
import com.finances.utils.UtilsCSV;
import com.finances.utils.UtilsYahooFinance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StockServiceImplTest {
    public static final String SYMBOL = "";
    public static final StockEntity NULL_STOCK_ENTITY = null;
    private static final Integer ID = 1;
    StockServiceImpl sut;

    @Mock
    private UtilsCSV utilsCSV;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private UtilsYahooFinance utilsYahooFinance;
    @Mock
    private Stock stock;
    @Mock
    private StockQuote stockQuote;
    @Mock
    private BigDecimal bigDecimal;
    @Mock
    private Calendar calendar;
    @Mock
    private StockEntity stockEntity;

    private List<StockEntity> stockEntities;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stockEntities = new ArrayList<>();
        stockEntities.add(stockEntity);
        sut = new StockServiceImpl(utilsCSV, stockRepository, utilsYahooFinance);
    }

    @Test
    public void when_getStock_given_symbol_and_not_exists_entity_on_database_then_stockRepostory_save_is_called() throws Exception {
        prepareMocks(NULL_STOCK_ENTITY);

        sut.getStock(SYMBOL);

        ArgumentCaptor<StockEntity> argumentCaptor = ArgumentCaptor.forClass(StockEntity.class);
        verify(stockRepository).save(argumentCaptor.capture());
        Assert.assertEquals(SYMBOL,argumentCaptor.getValue().getSymbol());
    }

    @Test
    public void when_getStock_given_symbol_and_exists_entity_on_database_then_stockRepostory_save_is_called() throws Exception {
        prepareMocks(stockEntity);
        when(stockEntity.getId()).thenReturn(ID);

        sut.getStock(SYMBOL);

        ArgumentCaptor<StockEntity> argumentCaptor = ArgumentCaptor.forClass(StockEntity.class);
        verify(stockRepository).save(argumentCaptor.capture());
        Assert.assertEquals(ID,argumentCaptor.getValue().getId());
    }

    @Test
    public void when_getAllStocksFromAPI_given_csv_data_then_actions_are_called_inOrder() throws Exception {
        prepareMocksAllStocksFromAPI();

        sut.getAllStocksFromAPI();

        InOrder inOrder = Mockito.inOrder(utilsYahooFinance, stockRepository);
        inOrder.verify(utilsYahooFinance).getStockFromYahooFinance(SYMBOL);
        inOrder.verify(stockRepository).save(any(StockEntity.class));
    }

    @Test
    public void when_getAllStocks_then_stockRepository_findAll_is_called() throws Exception {
        sut.getAllStocks();

        verify(stockRepository).findAll();
    }

    @Test
    public void when_deleteStock_given_ID_then_stockRepository_delete_is_called() throws Exception {
        sut.deleteStock(ID);

        verify(stockRepository).delete(ID);
    }

    @Test
    public void updateLastOldStocks() throws Exception {
        prepareMocksForUpdateLastOldStocks();

        sut.updateLastOldStocks();

        verify(utilsYahooFinance).getStockFromYahooFinance(SYMBOL);
    }

    private void prepareMocksForUpdateLastOldStocks() throws IOException {
        prepareMocks(stockEntity);
        when(stockEntity.getSymbol()).thenReturn(SYMBOL);
        when(stockRepository.getLastOldStocks()).thenReturn(stockEntities);
        when(utilsYahooFinance.getStockFromYahooFinance(SYMBOL)).thenReturn(stock);
    }

    private void prepareMocks(StockEntity stockEntity) throws IOException {
        when(utilsYahooFinance.getStockFromYahooFinance(SYMBOL)).thenReturn(stock);
        when(stockRepository.findBySymbol(SYMBOL)).thenReturn(stockEntity);
        when(stock.getQuote()).thenReturn(stockQuote);
        when(stock.getSymbol()).thenReturn(SYMBOL);
        when(stockQuote.getPreviousClose()).thenReturn(bigDecimal);
        when(stockQuote.getPrice()).thenReturn(bigDecimal);
        when(stockQuote.getLastTradeTime()).thenReturn(calendar);
    }

    private void prepareMocksAllStocksFromAPI() throws IOException {
        prepareMocks(stockEntity);
        Set<String> strings = new HashSet<>();
        strings.add(SYMBOL);
        when(utilsCSV.getAllSymbols(any(String.class))).thenReturn(strings);
    }

}