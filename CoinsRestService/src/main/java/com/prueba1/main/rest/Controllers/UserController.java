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

import com.prueba1.main.rest.Models.User;
import com.prueba1.main.rest.Services.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public ResponseEntity<Flux<User>> getAll() {
		return service.getAll();
	}

	@GetMapping("/users/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		return service.getByName(name);
	}

	@PostMapping("/users")
	public ResponseEntity<Mono<User>> add(@RequestBody User nuevo) {
		return service.save(nuevo.getId(), nuevo);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Mono<User>> modifyOne(@RequestBody User edit, @PathVariable Long id) {
		edit.setName(edit.getName());
		edit.setDNI(edit.getDNI());
		edit.setFiatMoney(edit.getFiatMoney());

		return service.modifyOne(edit.getId(), edit);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		return service.deleteById(id);
	}

	@DeleteMapping("/users")
	public ResponseEntity<?> removeAll() {
		return service.delete();
	}
}
