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

import com.prueba1.main.rest.modelo.CoinOfExchange;
import com.prueba1.main.rest.modelo.Repos.CoinOfExchangeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoinOfExchangeController {
	private final CoinOfExchangeRepository coinOfExchangeRepository;

	@GetMapping("/coinsOfExchanges")
	public ResponseEntity<?> getExchanges() {
		List<CoinOfExchange> coinsOfExchanges = coinOfExchangeRepository.findAll();
		if (coinsOfExchanges.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(coinsOfExchanges);
	}

	// 127.0.0.1:8080/coinsOfExchanges/name/coin/?name=Btc
	@GetMapping("/coinsOfExchanges/name/coin/")
	public ResponseEntity<?> getByCoinName(@RequestParam("name") String name) {
		List<CoinOfExchange> coinsOfExchanges = coinOfExchangeRepository.getCoinOfExchangeByCoinName(name);
		if (coinsOfExchanges.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(coinsOfExchanges);
	}

	// 127.0.0.1:8080/coinsOfExchanges/name/exchange/?name=Btc
	@GetMapping("/coinsOfExchanges/name/exchange/")
	public ResponseEntity<?> getByExchangeName(@RequestParam("name") String name) {
		List<CoinOfExchange> coinsOfExchanges = coinOfExchangeRepository.getCoinOfExchangeByExchangeName(name);
		if (coinsOfExchanges.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(coinsOfExchanges);
	}

	// 127.0.0.1:8080/coinsOfExchanges/?id=5
	@GetMapping("/coinsOfExchanges/")
	public ResponseEntity<?> getOne(@RequestParam("id") Long id) {
		CoinOfExchange coinsOfExchanges = coinOfExchangeRepository.findById(id).orElse(null);
		if (coinsOfExchanges == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(coinsOfExchanges);
	}

	// 127.0.0.1:8080/coinsOfExchanges
	@PostMapping("/coinsOfExchanges")
	public ResponseEntity<CoinOfExchange> addShop(@RequestBody CoinOfExchange nuevo) {
		CoinOfExchange coinOfExchange = coinOfExchangeRepository.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(coinOfExchange);
	}

	// 127.0.0.1:8080/coinsOfExchanges/5
	@PutMapping("/coinsOfExchanges/{id}")
	public ResponseEntity<?> modifyOne(@RequestBody CoinOfExchange edit, @PathVariable Long id) {
		CoinOfExchange coinOfExchange = coinOfExchangeRepository.findById(id).orElse(null);
		if (coinOfExchange == null)
			return ResponseEntity.notFound().build();

		coinOfExchange.setIdCoin(edit.getIdCoin());
		coinOfExchange.setIdExchange(edit.getIdExchange());

		return ResponseEntity.ok(coinOfExchangeRepository.save(coinOfExchange));
	}

	// 127.0.0.1:8080/coinsOfExchanges/5
	@DeleteMapping("/coinsOfExchanges/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		if (coinOfExchangeRepository.existsById(id)) {
			coinOfExchangeRepository.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	// 127.0.0.1:8080/coinsOfExchanges
	@DeleteMapping("/coinsOfExchanges")
	public ResponseEntity<?> removeAll() {
		coinOfExchangeRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}
}
