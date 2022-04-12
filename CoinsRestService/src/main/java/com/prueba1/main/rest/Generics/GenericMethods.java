package com.prueba1.main.rest.Generics;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class GenericMethods<T, ID extends Serializable> {
	// Metodos: getByName -> getRepository().findBy(null, null)

	private HttpStatus foundCode = HttpStatus.FOUND;
	private ResponseEntity<?> foundOk = ResponseEntity.ok(foundCode);

	public ResponseEntity<Mono<T>> save(ID id, T entity) {
		if (!existsById(id).equals(foundOk)) {
			getRepository().save(entity);
			return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(entity));
		}
		return ResponseEntity.status(foundCode).body(Mono.just(entity));
	}

	public ResponseEntity<Mono<T>> modifyOne(ID id, T entity) {
		if (existsById(id).equals(foundOk)) {
			getRepository().save(entity);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(Mono.just(entity));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<?> deleteById(ID id) {
		if (existsById(id).equals(foundOk)) {
			getRepository().deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<?> delete() {
		if (getRepository().count() > 0) {
			getRepository().deleteAll();
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<?> existsById(ID id) {
		if (getRepository().existsById(id))
			return ResponseEntity.ok(foundCode);

		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Flux<T>> getAll() {
		Flux<T> flux = Flux.fromIterable(getRepository().findAll());
		if (flux != Flux.empty())
			return ResponseEntity.ok(flux);

		return ResponseEntity.notFound().build();
	}

	public abstract JpaRepository<T, ID> getRepository();

}
