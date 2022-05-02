package br.com.th.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.th.springboot.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    

}
