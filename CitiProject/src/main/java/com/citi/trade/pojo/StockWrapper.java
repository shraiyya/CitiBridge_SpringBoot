package com.citi.trade.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import yahoofinance.Stock;

@AllArgsConstructor
public class StockWrapper {
    private Stock stock ;
    private LocalDateTime lastAccessed;
    
    
    public StockWrapper(final Stock stock)
    {
    	this.stock=stock;
    	lastAccessed = LocalDateTime.now();
    	
    }

	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public LocalDateTime getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(LocalDateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public StockWrapper withLastAccessed(LocalDateTime now) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
