package br.com.th.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.th.springboot.entity.Investment;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long>{
    

}
