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

import com.prueba1.main.rest.modelo.Coin;
import com.prueba1.main.rest.modelo.Repos.CoinRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoinController {
	private final CoinRepository coinRepository;

	//Hacer genericos y añadir programacion reactiva.
	
	@GetMapping("/coins")
	public ResponseEntity<?> getCoins() {
		List<Coin> coins = coinRepository.findAll();
		if (coins.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(coins);
	}

	// 127.0.0.1:8080/coins/name/?name=Btc
	@GetMapping("/coins/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		List<Coin> coins = coinRepository.getCoinsByName(name);
		if (coins.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(coins);
	}
	
	// 127.0.0.1:8080/coins/?id=5
	@GetMapping("/coins/")
	public ResponseEntity<?> getOne(@RequestParam("id") Long id) {
		Coin coin = coinRepository.findById(id).orElse(null);
		if (coin == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(coin);
	}

	// 127.0.0.1:8080/coins
	@PostMapping("/coins")
	public ResponseEntity<Coin> addCoin(@RequestBody Coin nuevo) {
		Coin guardado = coinRepository.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
	}

	//127.0.0.1:8080/coins/5
	@PutMapping("/coins/{id}")
	public ResponseEntity<?> modifyOne(@RequestBody Coin editar, @PathVariable Long id) {
		Coin coin = coinRepository.findById(id).orElse(null);
		if (coin == null)
			return ResponseEntity.notFound().build();

		coin.setName(editar.getName());
		coin.setPrice(editar.getPrice());
		
		return ResponseEntity.ok(coinRepository.save(coin));
	}
	
	//127.0.0.1:8080/coins/5
	@DeleteMapping("/coins/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		if (coinRepository.existsById(id)) {
			coinRepository.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	//127.0.0.1:8080/coins
	@DeleteMapping("/coins")
	public ResponseEntity<?> removeAll() {
		coinRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}
}
