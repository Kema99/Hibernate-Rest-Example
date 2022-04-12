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

import com.prueba1.main.rest.Models.Coin;
import com.prueba1.main.rest.Services.CoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CoinController {
	//Añadir en generic methods hash passwrd y pasarlo a users controller
	
	@Autowired
	private CoinService service;

	@GetMapping("/coins")
	public ResponseEntity<Flux<Coin>> getAll() {
		return service.getAll();
	}

	// 127.0.0.1:8080/coins/name/?name=Btc
	@GetMapping("/coins/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		return service.getByName(name);
	}

	@PostMapping("/coins")
	public ResponseEntity<Mono<Coin>> add(@RequestBody Coin entity) {
		return service.save(entity.getId(), entity);
	}

	@PutMapping("/coins")
	public ResponseEntity<Mono<Coin>> modifyOne(@RequestBody Coin edit) {
		edit.setName(edit.getName());
		edit.setPrice(edit.getPrice());
		return service.modifyOne(edit.getId(), edit);
	}

	@DeleteMapping("/coins/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		return service.deleteById(id);
	}

	@DeleteMapping("/coins")
	public ResponseEntity<?> removeAll() {
		return service.delete();
	}
}
