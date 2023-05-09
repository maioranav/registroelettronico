package org.vm93.registroelettronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Plesso;
import org.vm93.registroelettronico.service.PlessoService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/plessi")
public class PlessoController {
	
	@Autowired PlessoService service;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll(Pageable pageable) {
		return new ResponseEntity<>(service.getAllPlessi(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/random")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getRandom() {
		return new ResponseEntity<>(service.getRandom(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getRandom(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> postPlesso(@RequestBody Plesso p){
		return new ResponseEntity<>(service.savePlesso(p), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> putPlesso(@RequestBody Plesso p){
		return new ResponseEntity<>(service.updatePlesso(p), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deletePlesso(id), HttpStatus.OK);
	}

}
