package com.project.app.repository;

import com.project.app.model.Family;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

public interface FamilyRepository extends ReactiveMongoRepository<Family, String> {

  Mono<Family> findByfullname(String fullname);

  Mono<Family> findBynumberDocument(String numberDocument);

}
