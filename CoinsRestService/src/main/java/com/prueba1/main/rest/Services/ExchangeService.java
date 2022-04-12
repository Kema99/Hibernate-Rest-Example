package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Generics.GenericMethods;
import com.prueba1.main.rest.Models.Exchange;
import com.prueba1.main.rest.modelo.Repos.ExchangeRepository;

@Service
public class ExchangeService extends GenericMethods<Exchange, Long> {
	@Autowired
	private ExchangeRepository repository;

	@Override
	public JpaRepository<Exchange, Long> getRepository() {
		return repository;
	}

	public ResponseEntity<?> getByName(String name) {
		List<Exchange> exchanges = repository.getByName(name);
		if (exchanges.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(exchanges);
	}
}
