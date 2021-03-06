package com.prueba1.main.rest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
	@Query(value = "SELECT s FROM Shop s WHERE s.coin.name = :name")
	public List<Shop> getByName(@Param("name") String name);
}
