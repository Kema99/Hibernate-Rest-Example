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

import com.prueba1.main.rest.modelo.Shop;
import com.prueba1.main.rest.modelo.Repos.ShopRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShopController {
private final ShopRepository shopRepository;
	
	@GetMapping("/shopping")
	public ResponseEntity<?> getShopping()
	{
		List<Shop> shopping = shopRepository.findAll();
		if(shopping.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(shopping);
	}

	// 127.0.0.1:8080/shopping/name/?name=Btc
	@GetMapping("/shopping/name/")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {
		List<Shop> coins =	shopRepository.getCoinsByName(name);
		if (coins.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(coins);
	}
	// 127.0.0.1:8080/shop/?id=5
		@GetMapping("/shop/")
		public ResponseEntity<?> getOne(@RequestParam("id") Long id) {
			Shop shop = shopRepository.findById(id).orElse(null);
			if (shop == null)
				return ResponseEntity.notFound().build();

			return ResponseEntity.ok(shop);
		}

		// 127.0.0.1:8080/shop
		@PostMapping("/shop")
		public ResponseEntity<Shop> addShop(@RequestBody Shop nuevo) {
			Shop guardado = shopRepository.save(nuevo);
			return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
		}

		//127.0.0.1:8080/shop/5
		@PutMapping("/shop/{id}")
		public ResponseEntity<?> modifyOne(@RequestBody Shop edit, @PathVariable Long id) {
			Shop shop = shopRepository.findById(id).orElse(null);
			if (shop == null)
				return ResponseEntity.notFound().build();

			shop.setCoin(edit.getCoin());
			shop.setShopPrice(edit.getShopPrice());
			shop.setDate(edit.getDate());
			
			return ResponseEntity.ok(shopRepository.save(shop));
		}
		
		//127.0.0.1:8080/shop/5
		@DeleteMapping("/shop/{id}")
		public ResponseEntity<?> removeOne(@PathVariable Long id) {
			if (shopRepository.existsById(id)) {
				shopRepository.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			}
			return ResponseEntity.notFound().build();
		}

		//127.0.0.1:8080/shop
		@DeleteMapping("/shop")
		public ResponseEntity<?> removeAll() {
			shopRepository.deleteAll();
			return ResponseEntity.noContent().build();
		}
}
