package com.project.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.app.model.Family;
import com.project.app.repository.FamilyRepository;
import com.project.app.service.FamilyInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FamilyServiceImpl implements FamilyInterface {
	
	@Autowired private FamilyRepository familyRepository;

	  @Override
	  public Flux<Family> findAll() {
	    return familyRepository.findAll();
	  }

	  @Override
	  public Mono<Family> findById(String id) {
	    return familyRepository.findById(id);
	  }

	  @Override
	  public Mono<Family> save(Family f) {
	    return familyRepository.save(f);
	  }

	  @Override
	  public Mono<Void> delete(Family f) {
	    return familyRepository.delete(f);
	  }

	  @Override
	  public Mono<Family> findBynumberDocument(String numberDocument) {
	    return familyRepository.findBynumberDocument(numberDocument);
	  }

	  @Override
	  public Mono<Family> findByFullName(String name) {
	    return familyRepository.findByFullName(name);
	  }
}
