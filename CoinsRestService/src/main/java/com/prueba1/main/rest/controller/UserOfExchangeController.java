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

import com.prueba1.main.rest.modelo.UserOfExchange;
import com.prueba1.main.rest.modelo.Repos.UserOfExchangeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserOfExchangeController {
	private final UserOfExchangeRepository userOfExchangeRepository;

	@GetMapping("/usersOfExchanges")
	public ResponseEntity<?> getUser() {
		List<UserOfExchange> users = userOfExchangeRepository.findAll();
		if (users.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(users);
	}

	// 127.0.0.1:8080/usersOfExchanges/name/user/?name=Jhon
	@GetMapping("/usersOfExchanges/name/user/")
	public ResponseEntity<?> getByUserName(@RequestParam("name") String name) {
		List<UserOfExchange> usersOfExchanges = userOfExchangeRepository.getUserOfExchangeByUserName(name);
		if (usersOfExchanges.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usersOfExchanges);
	}

	// 127.0.0.1:8080/usersOfExchanges/name/exchange/?name=Binance
	@GetMapping("/usersOfExchanges/name/exchange/")
	public ResponseEntity<?> getByExchangeName(@RequestParam("name") String name) {
		List<UserOfExchange> usersOfExchange = userOfExchangeRepository.getUserOfExchangeByExchangeName(name);
		if (usersOfExchange.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usersOfExchange);
	}

	// 127.0.0.1:8080/usersOfExchanges/?id=5
	@GetMapping("/usersOfExchanges/")
	public ResponseEntity<?> getOne(@RequestParam("id") Long id) {
		UserOfExchange userOfExchange = userOfExchangeRepository.findById(id).orElse(null);
		if (userOfExchange == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(userOfExchange);
	}

	// 127.0.0.1:8080/usersOfExchanges
	@PostMapping("/usersOfExchanges")
	public ResponseEntity<UserOfExchange> addShop(@RequestBody UserOfExchange nuevo) {
		UserOfExchange userOfExchange = userOfExchangeRepository.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(userOfExchange);
	}

	// 127.0.0.1:8080/usersOfExchanges/5
	@PutMapping("/usersOfExchanges/{id}")
	public ResponseEntity<?> modifyOne(@RequestBody UserOfExchange edit, @PathVariable Long id) {
		UserOfExchange userOfExchange = userOfExchangeRepository.findById(id).orElse(null);
		if (userOfExchange == null)
			return ResponseEntity.notFound().build();

		userOfExchange.setIdUser(edit.getIdUser());
		userOfExchange.setIdExchange(edit.getIdExchange());

		return ResponseEntity.ok(userOfExchangeRepository.save(userOfExchange));
	}

	// 127.0.0.1:8080/usersOfExchanges/5
	@DeleteMapping("/usersOfExchanges/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		if (userOfExchangeRepository.existsById(id)) {
			userOfExchangeRepository.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	// 127.0.0.1:8080/usersOfExchanges
	@DeleteMapping("/usersOfExchanges")
	public ResponseEntity<?> removeAll() {
		userOfExchangeRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}
}
