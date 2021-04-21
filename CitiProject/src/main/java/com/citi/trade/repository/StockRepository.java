package com.citi.trade.repository;
import com.citi.trade.model.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
	List<Stock> findByUserId(long user_id);

}
