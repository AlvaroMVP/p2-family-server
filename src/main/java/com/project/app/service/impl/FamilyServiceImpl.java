package com.project.app.service.impl;

import com.project.app.model.Family;
import com.project.app.repository.FamilyRepository;
import com.project.app.service.FamilyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FamilyServiceImpl implements FamilyInterface {

  @Autowired 
  private FamilyRepository familyRepository;

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
  public Mono<Family> findByfullname(String fullname) {
    return familyRepository.findByfullname(fullname);
  }
}
