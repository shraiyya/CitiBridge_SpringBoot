package com.citi.trade.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citi.trade.pojo.StockWrapper;

import lombok.AllArgsConstructor;
import yahoofinance.YahooFinance;

@Service
@AllArgsConstructor
public class StockService {
	private final RefreshService refreshService = new RefreshService();
	
	public StockWrapper findStock(final String ticker)
	{
		try {
			return new StockWrapper(YahooFinance.get(ticker));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<StockWrapper> findStock(final List<String> tickers)
	{
		return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
	}
   
	public BigDecimal findPrice(final StockWrapper stock) throws IOException
	{
		
		return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getPrice();
	}
	public BigDecimal findLastChangePercent(final StockWrapper stock) throws IOException
	{
		return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeInPercent();
	}
	
	public BigDecimal findChangeFrom200MeanPercent(final StockWrapper stock) throws IOException
	{
		return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeFromAvg200InPercent();
	}
	
}
