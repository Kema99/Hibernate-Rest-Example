package com.prueba1.main.rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Models.CoinOfExchange;
import com.prueba1.main.rest.Services.CoinOfExchangeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CoinOfExchangeController {

	@Autowired
	private CoinOfExchangeService service;

	@GetMapping("/coinsOfExchanges")
	public ResponseEntity<Flux<CoinOfExchange>> getAll() {
		return service.getAll();
	}

	// 127.0.0.1:8080/coinsOfExchanges/name/exchange/?name=Binance
	@GetMapping("/coinsOfExchanges/name/exchange")
	public ResponseEntity<?> getByExchangeName(@RequestParam("name") String name) {
		return service.getByExchangeName(name);
	}

	@GetMapping("/coinsOfExchanges/name/coin")
	public ResponseEntity<?> getByCoinName(@RequestParam("name") String name) {
		return service.getByCoinName(name);
	}

	@PostMapping("/coinsOfExchanges")
	public ResponseEntity<Mono<CoinOfExchange>> add(@RequestBody CoinOfExchange entity) {
		return service.save(entity.getId(), entity);
	}

	@PutMapping("/coinsOfExchanges")
	public ResponseEntity<Mono<CoinOfExchange>> modifyOne(@RequestBody CoinOfExchange edit) {
		edit.setIdCoin(edit.getIdCoin());
		edit.setIdExchange(edit.getIdExchange());
		return service.modifyOne(edit.getId(), edit);
	}

	@DeleteMapping("/coinsOfExchanges/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		return service.deleteById(id);
	}

	@DeleteMapping("/coinsOfExchanges")
	public ResponseEntity<?> removeAll() {
		return service.delete();
	}
}
