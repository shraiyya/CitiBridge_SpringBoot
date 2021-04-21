package com.citi.trade.controller;
import java.io.IOException; 
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import yahoofinance.*;
import com.citi.trade.model.RStock;
import com.citi.trade.service.SavedStocksService;
import com.citi.trade.service.UserService;

@RestController
@RequestMapping("/finance_api")
@Component
public class FinanceAPIController {

	@Autowired
	SavedStocksService saved_stocks_service;
	
	@Autowired
	UserService user_service;
	
	Map<String, List<String>> sector_wise_companies = Map.ofEntries(
			new AbstractMap.SimpleEntry<String, List<String>>("Automobile",
					new ArrayList<>(Arrays.asList("BAJAJ-AUTO.NS", "EICHERMOT.NS", "HEROMOTOCO.NS", "M&M.NS",
							"MARUTI.NS", "TATAMOTORS.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Banking",
					new ArrayList<>(Arrays.asList("AXISBANK.NS", "HDFCBANK.NS", "ICICIBANK.NS ", " INDUSINDBK.NS ",
							"KOTAKBANK.NS ", " SBIN.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Cement",
					new ArrayList<>(Arrays.asList("ASIANPAINT.NS", "BRITANNIA.NS", "HINDUNILVR.NS", "ITC.NS",
							"NESTLEIND.NS", "TITAN.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Energy",
					new ArrayList<>(Arrays.asList("BPCL.NS", "GAIL.NS", "IOC.NS", "ONGC.NS", "RELIANCE.NS", "NTPC.NS",
							"POWERGRID.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Information Technology",
					new ArrayList<>(Arrays.asList("HCLTECH.NS", "INFY.NS", "TCS.NS", "TECHM.NS", "WIPRO.NS"))));

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/fetch_data/{category}")
	//@ApiOperation(value = "fetch sector data", notes = "Fetch data from yahoo finance api for particular category")
	public List<RStock> FetchHistoricData(@PathVariable("category") String category) {
		
		ObjectMapper mapper = new ObjectMapper();
		List<RStock> res = new ArrayList<>();
		
		try {
			List<String> companies = sector_wise_companies.get(category);

			String[] tmp = new String[companies.size()];
			for (int i = 0; i < tmp.length; i++)
				tmp[i] = companies.get(i);

			Map<String, Stock> stocks = YahooFinance.get(tmp);
			List<Stock> sorted_stocks = new ArrayList<>(stocks.values());

			Collections.sort(sorted_stocks, new Comparator<Stock>() {
				public int compare(Stock s1, Stock s2) {
					return s2.getQuote().getPrice().compareTo(s1.getQuote().getPrice());
				}
			});

			if (stocks.size() == 0)
				System.out.print("Unable to fetch data from yahoo api.");
			else {
				for (Stock company : sorted_stocks) {
					if (company == null)
						continue;
					if (res.size() == 5)
						break;
					RStock t = new RStock(company.getSymbol(),company, category);
					System.out.println(t);
					res.add(t);
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String jsonStr = null;
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try { 
	  
	            // get Oraganisation object as a json string 
	             jsonStr = Obj.writeValueAsString(res); 
	  
	            // Displaying JSON String 
	            System.out.println(jsonStr); 
	        } 
	  
	        catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
		
	
		return res;

	}
	public List<RStock> FetchHistoricDataALL() {
		
		ObjectMapper mapper = new ObjectMapper();
		List<RStock> res = new ArrayList<>();
		
		try {
			for (String sector : sector_wise_companies.keySet()) {
				List<String> companies = sector_wise_companies.get(sector);
				
				String[] tmp = new String[companies.size()];
				for (int i = 0; i < tmp.length; i++)
					tmp[i] = companies.get(i);
				
				Map<String, Stock> stocks = YahooFinance.get(tmp);
				List<Stock> all_stocks = new ArrayList<>(stocks.values());
				
				for (Stock company : all_stocks) {
					if (company == null)
						continue;
					RStock t = new RStock(company.getSymbol(),company, sector);
					System.out.println(t);
					res.add(t);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return res;
	}

}
