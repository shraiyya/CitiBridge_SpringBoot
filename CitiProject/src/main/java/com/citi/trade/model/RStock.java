package com.citi.trade.model;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import yahoofinance.Stock;

@ToString
@Entity
@Setter @Getter
public class RStock{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) private long stockid;
	//@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="stock_category_id", referencedColumnName="sectorid")
	//Sector stockCategory;
	String sector;
	String companyName;
	String symbol;
	BigDecimal price;
	BigDecimal bid;
	BigDecimal ask;
	BigDecimal change;
	
	public RStock(String symbol,Stock s,String sector)
	{
		this.symbol = symbol;
		this.companyName = s.getName();
		this.sector = sector;
		this.price = s.getQuote().getPrice();
		this.bid = s.getQuote().getBid();
		this.ask = s.getQuote().getAsk();
		this.change = s.getQuote().getChange();
	}
	
	public RStock(){}
	
}