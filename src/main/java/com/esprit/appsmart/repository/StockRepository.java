package com.esprit.appsmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.appsmart.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

}
