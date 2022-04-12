package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Generics.GenericMethods;
import com.prueba1.main.rest.Models.Shop;
import com.prueba1.main.rest.modelo.Repos.ShopRepository;

@Service
public class ShopService extends GenericMethods<Shop, Long> {

	@Autowired
	private ShopRepository repository;

	@Override
	public JpaRepository<Shop, Long> getRepository() {
		return repository;
	}

	public ResponseEntity<?> getByName(String name) {
		List<Shop> shops = repository.getByName(name);
		if (shops.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(shops);
	}
}
