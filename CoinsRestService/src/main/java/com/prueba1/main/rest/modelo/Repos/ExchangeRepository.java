package com.prueba1.main.rest.modelo.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.modelo.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>{
	@Query(value = "SELECT e FROM Exchange e WHERE e.name = :name")
	public List<Exchange> getExchangesByName(@Param("name") String name);
	
	@Query(value = "SELECT COUNT(e) FROM Exchange e")
	public long countExchanges();
}
