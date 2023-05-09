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
import org.vm93.registroelettronico.model.Messaggio;
import org.vm93.registroelettronico.service.MessaggioService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/msgs")
public class MessaggioController {
	
	@Autowired MessaggioService service;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll(Pageable pageable) {
		return new ResponseEntity<>(service.getAllMessaggio(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getRandom(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> postCorso(@RequestBody Messaggio c){
		return new ResponseEntity<>(service.saveMessaggio(c), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> putCorso(@RequestBody Messaggio c){
		return new ResponseEntity<>(service.updateMessaggio(c), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deleteMessaggio(id), HttpStatus.OK);
	}

}
