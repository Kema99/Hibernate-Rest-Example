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

import com.prueba1.main.rest.Models.Exchange;
import com.prueba1.main.rest.Services.ExchangeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ExchangeController {

	@Autowired
	private ExchangeService service;

	@GetMapping("/exchanges")
	public ResponseEntity<Flux<Exchange>> getAll() {
		return service.getAll();
	}

	@GetMapping("/exchanges/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		return service.getByName(name);
	}

	@PostMapping("/exchanges")
	public ResponseEntity<Mono<Exchange>> add(@RequestBody Exchange nuevo) {
		return service.save(nuevo.getId(), nuevo);
	}

	@PutMapping("/exchanges/{id}")
	public ResponseEntity<Mono<Exchange>> modifyOne(@RequestBody Exchange edit, @PathVariable Long id) {
		edit.setName(edit.getName());
		return service.modifyOne(edit.getId(), edit);
	}

	@DeleteMapping("/exchanges/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		return service.deleteById(id);
	}

	@DeleteMapping("/exchanges")
	public ResponseEntity<?> removeAll() {
		return service.delete();
	}
}