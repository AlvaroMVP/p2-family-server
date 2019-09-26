package com.project.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.app.model.Family;

import reactor.core.publisher.Mono;

public interface FamilyRepository extends ReactiveMongoRepository<Family, String> {

	  Mono<Family> findByFullName(String name);

	  Mono<Family> findBynumberDocument(String numberDocument);

}
