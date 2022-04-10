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

import com.prueba1.main.rest.modelo.User;
import com.prueba1.main.rest.modelo.Repos.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserRepository userRepository;

	@GetMapping("/users")
	public ResponseEntity<?> getUser() {
		List<User> user = userRepository.findAll();
		if (user.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(user);
	}


	// 127.0.0.1:8080/users/name/?name=Pepe
	@GetMapping("/users/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		List<User> users = userRepository.getUsersByName(name);
		if (users.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(users);
	}

	// 127.0.0.1:8080/users/?id=5
	@GetMapping("/users/")
	public ResponseEntity<?> getOne(@RequestParam("id") Long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(user);
	}

	// 127.0.0.1:8080/users
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User nuevo) {
		User user = userRepository.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	// 127.0.0.1:8080/users/5
	@PutMapping("/users/{id}")
	public ResponseEntity<?> modifyOne(@RequestBody User edit, @PathVariable Long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null)
			return ResponseEntity.notFound().build();

		user.setName(edit.getName());
		user.setDNI(edit.getDNI());
		user.setFiatMoney(edit.getFiatMoney());

		return ResponseEntity.ok(userRepository.save(user));
	}
	
	// 127.0.0.1:8080/users/5
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	// 127.0.0.1:8080/users
	@DeleteMapping("/users")
	public ResponseEntity<?> removeAll() {
		userRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}
}
