package com.prueba1.main.rest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.Coin;

public interface CoinRepository extends JpaRepository<Coin, Long> {
	@Query(value = "SELECT c FROM Coin c WHERE c.name = :name")
	public List<Coin> getByName(@Param("name") String name);

}
