package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Generics.GenericMethods;
import com.prueba1.main.rest.Models.CoinOfExchange;
import com.prueba1.main.rest.modelo.Repos.CoinOfExchangeRepository;

@Service
public class CoinOfExchangeService extends GenericMethods<CoinOfExchange, Long> {

	@Autowired
	private CoinOfExchangeRepository repository;

	@Override
	public JpaRepository<CoinOfExchange, Long> getRepository() {
		return repository;
	}

	public ResponseEntity<?> getByExchangeName(String name) {
		List<CoinOfExchange> coinsOfExchanges = repository.getByExchangeName(name);
		if (!coinsOfExchanges.isEmpty())
			return ResponseEntity.ok(coinsOfExchanges);

		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<?> getByCoinName(String name) {
		List<CoinOfExchange> coinsOfExchanges = repository.getByCoinName(name);
		if (!coinsOfExchanges.isEmpty())
			return ResponseEntity.ok(coinsOfExchanges);

		return ResponseEntity.noContent().build();
	}
}
