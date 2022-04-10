package com.prueba1.main.rest.modelo.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.modelo.*;


public interface CoinOfExchangeRepository extends JpaRepository<CoinOfExchange, Long>{
	@Query(value = "SELECT cof FROM CoinOfExchange cof WHERE cof.idCoin.name = :name")
	public List<CoinOfExchange> getCoinOfExchangeByCoinName(@Param("name") String name);
	@Query(value = "SELECT cof FROM CoinOfExchange cof WHERE cof.idExchange.name = :name")
	public List<CoinOfExchange> getCoinOfExchangeByExchangeName(@Param("name") String name);
}
