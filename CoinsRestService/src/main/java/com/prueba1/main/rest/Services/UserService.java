package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Generics.GenericMethods;
import com.prueba1.main.rest.Models.User;
import com.prueba1.main.rest.modelo.Repos.UserRepository;

@Service
public class UserService extends GenericMethods<User, Long> {
	@Autowired
	private UserRepository repository;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return repository;
	}

	public ResponseEntity<?> getByName(String name) {
		List<User> users = repository.getByName(name);
		if (users.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}
}
