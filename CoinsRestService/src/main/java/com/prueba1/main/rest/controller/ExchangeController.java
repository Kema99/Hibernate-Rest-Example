package com.prueba1.main.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.modelo.Exchange;
import com.prueba1.main.rest.modelo.Repos.ExchangeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExchangeController {
	private final ExchangeRepository exchangeRepository;

	// Hacer maxRegistros de tablas
	@GetMapping("/exchanges")
	public ResponseEntity<?> getExchanges() {
		List<Exchange> exchange = exchangeRepository.findAll();
		if (exchange.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(exchange);
	}

	// 127.0.0.1:8080/exchanges/name/?name=Binance
	@GetMapping("/exchanges/count")
	public ResponseEntity<?> count() {
		long countedRecords = exchangeRepository.count();
		if (countedRecords == 0)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(countedRecords);
	}

	// 127.0.0.1:8080/exchanges/name/?name=Binance
	@GetMapping("/exchanges/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		List<Exchange> exchanges = exchangeRepository.getExchangesByName(name);
		if (exchanges.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(exchanges);
	}

	// 127.0.0.1:8080/exchanges/?id=5
	@GetMapping("/exchanges/")
	public ResponseEntity<?> getOne(@RequestParam("id") Long id) {
		Exchange exchange = exchangeRepository.findById(id).orElse(null);
		if (exchange == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(exchange);
	}

	// 127.0.0.1:8080/exchanges
	@PostMapping("/exchanges")
	public ResponseEntity<Exchange> addCoin(@RequestBody Exchange nuevo) {
		Exchange guardado = exchangeRepository.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
	}

	// 127.0.0.1:8080/exchanges/5
	@PutMapping("/exchanges/{id}")
	public ResponseEntity<?> modifyOne(@RequestBody Exchange editar, @PathVariable Long id) {
		Exchange exchange = exchangeRepository.findById(id).orElse(null);
		if (exchange == null)
			return ResponseEntity.notFound().build();

		exchange.setName(editar.getName());

		return ResponseEntity.ok(exchangeRepository.save(exchange));
	}

	// 127.0.0.1:8080/exchanges/5
	@DeleteMapping("/exchanges/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		if (exchangeRepository.existsById(id)) {
			exchangeRepository.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	// 127.0.0.1:8080/exchanges
	@DeleteMapping("/exchanges")
	public ResponseEntity<?> removeAll() {
		exchangeRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}
}
