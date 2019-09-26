package com.project.app.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.model.Family;
import com.project.app.service.FamilyInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** The type Family controller. */
@RestController
@RequestMapping("/api/v1.0")
public class FamilyController {

	@Autowired
	FamilyInterface familyService;

	/**
	 * Create mono.
	 *
	 * @param familyMono the family mono
	 * @return the mono
	 */
	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<Family> familyMono) {

		Map<String, Object> data = new HashMap<String, Object>();

		return familyMono.flatMap(family -> {
			if (family.getBirthdate() == null) {
				family.setBirthdate(new Date());
			}

			return familyService.save(family).map(p -> {
				data.put("family", p);
				data.put("message", "family registered");
				data.put("timestamp", new Date());
				return ResponseEntity.created(URI.create("/api/v1.0".concat(p.getId())))
						.contentType(MediaType.APPLICATION_JSON_UTF8).body(data);
			});
		});
	}

	/**
	 * Find all mono.
	 *
	 * @return the mono
	 */
	@GetMapping("/family")
	public Mono<ResponseEntity<Flux<Family>>> findAll() {
		return Mono
				.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(familyService.findAll()));
	}

	/**
	 * Find by id mono.
	 *
	 * @param id the id
	 * @return the mono
	 */
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Family>> findById(@PathVariable String id) {
		return familyService.findById(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * Update mono.
	 *
	 * @param family the family
	 * @param id     the id
	 * @return the mono
	 */
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Family>> save(@RequestBody Family family, @PathVariable String id) {
		return familyService.findById(id).flatMap(p -> {
			  p.setFullname(family.getFullname());
              p.setGender(family.getGender());
              p.setBirthdate(family.getBirthdate());
              p.setTypeID(family.getTypeID());
              p.setNumberDocument(family.getNumberDocument());
              p.setCreatedAt(family.getCreatedAt());
              p.setIdFamily(family.getIdFamily());
			
			return familyService.save(p);
		}).map(p -> ResponseEntity.created(URI.create("/api/v1.0".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	/**
	 * Eliminar mono.
	 *
	 * @param id the id
	 * @return the mono
	 */
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id) {
		return familyService.findById(id).flatMap(p -> {
			return familyService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Find by id number mono.
	 *
	 * @param idNumber the id number
	 * @return the mono
	 */
	@GetMapping("numberDocument/{numberDocument}")
	public Mono<ResponseEntity<Family>> findByIdNumber(@PathVariable String numberDocument) {
		return familyService.findBynumberDocument(numberDocument)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * Find by full name mono.
	 *
	 * @param name the name
	 * @return the mono
	 */
	@GetMapping("name/{name}")
	public Mono<ResponseEntity<Family>> findByFullName(@PathVariable String name) {
		return familyService.findByFullName(name)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
