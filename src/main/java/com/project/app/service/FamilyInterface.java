package com.project.app.service;

import com.project.app.model.Family;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FamilyInterface {

	Flux<Family> findAll();

	Mono<Family> findById(String id);

	Mono<Family> save(Family f);

	Mono<Void> delete(Family f);

	Mono<Family> findBynumberDocument(String numberDocument);

	Mono<Family> findByFullName(String name);
}
