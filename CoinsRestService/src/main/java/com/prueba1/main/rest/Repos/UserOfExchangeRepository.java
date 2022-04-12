package com.prueba1.main.rest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.UserOfExchange;

public interface UserOfExchangeRepository extends JpaRepository<UserOfExchange, Long> {
	@Query(value = "SELECT uof FROM UserOfExchange uof WHERE uof.idUser.name = :name")
	public List<UserOfExchange> getByUserName(@Param("name") String name);

	@Query(value = "SELECT uof FROM UserOfExchange uof WHERE uof.idExchange.name = :name")
	public List<UserOfExchange> getByExchangeName(@Param("name") String name);
}
