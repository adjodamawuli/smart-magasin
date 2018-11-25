package com.esprit.appsmart.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.appsmart.exception.ResourceNotFoundException;
import com.esprit.appsmart.model.Stock;
import com.esprit.appsmart.repository.StockRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/smartStock")
public class StockController {
	 @Autowired
	    StockRepository stockRepository;

	    @GetMapping("/stocks")
	    public List<Stock> getAllStocks() {
	        return stockRepository.findAll();
	    }

	    @PostMapping("/stocks")
	    public Stock createStock(@Valid @RequestBody Stock stock) {
	    	stock.setCreatedAt(new Date());
	    	stock.setUpdatedAT(new Date());
	        return stockRepository.save(stock);
	    }

	    @GetMapping("/stocks/{id}")
	    public Stock getStockById(@PathVariable(value = "id") Long stockId) {
	        return stockRepository.findById(stockId)
	                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stockId));
	    }

	    @PutMapping("/stocks/{id}")
	    public Stock updateStock(@PathVariable(value = "id") Long stockId,
	                                           @Valid @RequestBody Stock stockDetails) {

	        Stock stock = stockRepository.findById(stockId)
	                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stockId));

	        stock.setLibelleFarine(stockDetails.getLibelleFarine());
	        stock.setQte(stockDetails.getQte());
	        stock.setUpdatedAT(new Date());

	        Stock updatedStock = stockRepository.save(stock);
	        return updatedStock;
	    }

	    @DeleteMapping("/stocks/{id}")
	    public ResponseEntity<?> deleteStock(@PathVariable(value = "id") Long stockId) {
	        Stock stock = stockRepository.findById(stockId)
	                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stockId));

	        stockRepository.delete(stock);

	        return ResponseEntity.ok().build();
	    }
}
