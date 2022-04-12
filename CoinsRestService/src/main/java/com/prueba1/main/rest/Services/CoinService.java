package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Generics.GenericMethods;
import com.prueba1.main.rest.Models.Coin;
import com.prueba1.main.rest.Repos.CoinRepository;

@Service
public class CoinService extends GenericMethods<Coin, Long> {

	@Autowired
	private CoinRepository repository;

	@Override
	public JpaRepository<Coin, Long> getRepository() {
		return repository;
	}

	// Personalized query
	public ResponseEntity<?> getByName(String name) {
		List<Coin> coins = repository.getByName(name);
		if (coins.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(coins);
	}
}
