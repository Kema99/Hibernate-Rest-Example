package com.prueba1.main.rest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.*;

public interface CoinOfExchangeRepository extends JpaRepository<CoinOfExchange, Long> {
	@Query(value = "SELECT cof FROM CoinOfExchange cof WHERE cof.idCoin.name = :name")
	public List<CoinOfExchange> getByCoinName(@Param("name") String name);

	@Query(value = "SELECT cof FROM CoinOfExchange cof WHERE cof.idExchange.name = :name")
	public List<CoinOfExchange> getByExchangeName(@Param("name") String name);
}
