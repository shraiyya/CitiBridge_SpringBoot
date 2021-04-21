package com.citi.trade;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.citi.trade.pojo.StockWrapper;
import com.citi.trade.service.StockService;

@SpringBootApplication
 public class StockServiceTest {
	@Autowired
	private StockService stockservice;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException, InterruptedException {
		
		final StockWrapper stock = stockservice.findStock("UU.L");
		System.out.println(stock.getStock());
		
		final BigDecimal price = stockservice.findPrice(stock);
		System.out.println(price);
		
		final BigDecimal change = stockservice.findLastChangePercent(stock);
		System.out.println(change);
		
		final BigDecimal mean200DayPercent = stockservice.findChangeFrom200MeanPercent(stock);
		System.out.println(mean200DayPercent);
		
		
		final List<StockWrapper> stocks =stockservice.findStock(Arrays.asList("GOOG","AMZN"));
		 findPrices(stocks);
		 
		 
		 
		 
		 findPrices(stocks);
		 
		

	
	}
	private void findPrices(List<StockWrapper> stocks) 
	{
		stocks.forEach(stock->{
			try
			{
				System.out.println(stockservice.findPrice(stock));
			}
			catch(IOException e)
			{
				//ignore
			}
		});
	}
		
	public static void main(String[] args) {
		SpringApplication.run(StockServiceTest.class, args);
	}
		
	    
	}

