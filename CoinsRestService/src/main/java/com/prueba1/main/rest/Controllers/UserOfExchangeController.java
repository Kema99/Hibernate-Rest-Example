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

import com.prueba1.main.rest.Models.UserOfExchange;
import com.prueba1.main.rest.Services.UserOfExchangeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserOfExchangeController {
	
	@Autowired
	private UserOfExchangeService service;

	@GetMapping("/usersOfExchanges")
	public ResponseEntity<Flux<UserOfExchange>> getAll() {
		return service.getAll();
	}

	@GetMapping("/usersOfExchanges/name/exchange")
	public ResponseEntity<?> getByExchangeName(@RequestParam("name") String name) {
		return service.getByExchangeName(name);
	}

	@GetMapping("/usersOfExchanges/name/user")
	public ResponseEntity<?> getByUserName(@RequestParam("name") String name) {
		return service.getByUserName(name);
	}

	@PostMapping("/usersOfExchanges")
	public ResponseEntity<Mono<UserOfExchange>> add(@RequestBody UserOfExchange entity) {
		return service.save(entity.getId(), entity);
	}

	@PutMapping("/usersOfExchanges")
	public ResponseEntity<Mono<UserOfExchange>> modifyOne(@RequestBody UserOfExchange edit) {
		edit.setIdUser(edit.getIdUser());
		edit.setIdExchange(edit.getIdExchange());
		return service.modifyOne(edit.getId(), edit);
	}

	@DeleteMapping("/usersOfExchanges/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		return service.deleteById(id);
	}

	@DeleteMapping("/usersOfExchanges")
	public ResponseEntity<?> removeAll() {
		return service.delete();
	}
}
