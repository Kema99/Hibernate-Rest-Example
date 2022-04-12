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

import com.prueba1.main.rest.Models.Shop;
import com.prueba1.main.rest.Services.ShopService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ShopController {
	@Autowired
	private ShopService service;

	@GetMapping("/shops")
	public ResponseEntity<Flux<Shop>> getAll() {
		return service.getAll();
	}

	@GetMapping("/shops/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		return service.getByName(name);
	}

	@PostMapping("/shops")
	public ResponseEntity<Mono<Shop>> add(@RequestBody Shop entity) {
		return service.save(entity.getId(), entity);
	}

	@PutMapping("/shops")
	public ResponseEntity<Mono<Shop>> modifyOne(@RequestBody Shop edit) {
		edit.setCoin(edit.getCoin());
		edit.setShopPrice(edit.getShopPrice());
		edit.setDate(edit.getDate());
		return service.modifyOne(edit.getId(), edit);
	}

	@DeleteMapping("/shops/{id}")
	public ResponseEntity<?> removeOne(@PathVariable Long id) {
		return service.deleteById(id);
	}

	@DeleteMapping("/shops")
	public ResponseEntity<?> removeAll() {
		return service.delete();
	}
}
