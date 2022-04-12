package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Generics.GenericMethods;
import com.prueba1.main.rest.Models.UserOfExchange;
import com.prueba1.main.rest.Repos.UserOfExchangeRepository;

@Service
public class UserOfExchangeService extends GenericMethods<UserOfExchange, Long> {
	@Autowired
	private UserOfExchangeRepository repository;

	@Override
	public JpaRepository<UserOfExchange, Long> getRepository() {
		return repository;
	}

	public ResponseEntity<?> getByExchangeName(String name) {
		List<UserOfExchange> usersOfExchanges = repository.getByExchangeName(name);
		if (!usersOfExchanges.isEmpty())
			return ResponseEntity.ok(usersOfExchanges);

		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<?> getByUserName(String name) {
		List<UserOfExchange> usersOfExchanges = repository.getByUserName(name);
		if (!usersOfExchanges.isEmpty())
			return ResponseEntity.ok(usersOfExchanges);

		return ResponseEntity.noContent().build();
	}
}
