package com.prueba1.main.rest.modelo.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.User;

//@ComponentScan(basePackages = {"com.prueba1.main.Repos"})
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT u FROM User u WHERE u.name = :name")
	public List<User> getByName(@Param("name") String name);
}
