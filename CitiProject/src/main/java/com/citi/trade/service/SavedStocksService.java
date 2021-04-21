package com.citi.trade.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.citi.trade.model.Stock;
import com.citi.trade.repository.*;

@Service
public class SavedStocksService {

	@Autowired
    private StockRepository repository;
	
	
	public SavedStocksService() {
    }

    public Stock saveStock(Stock s) {
        return repository.save(s);
    }

    public Optional<Stock> getSavedStocks(long id) {
        return repository.findById(id);
    }

    public Collection<Stock> getAllSavedStocksById(long user_id)
    {
    	return repository.findByUserId(user_id);
    }
    
    public List<Stock> getAllSavedStocks()
    {
    	return repository.findAll();
    }
}
