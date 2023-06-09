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
import org.vm93.registroelettronico.service.CorsoService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/corsi")
public class CorsoController {
	
	@Autowired CorsoService service;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll(Pageable pageable) {
		return new ResponseEntity<>(service.getAllCorsi(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAllList() {
		return new ResponseEntity<>(service.getAllCorsi(), HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getRandom(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> postCorso(@RequestBody Corso c){
		return new ResponseEntity<>(service.saveCorso(c), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> putCorso(@RequestBody Corso c){
		return new ResponseEntity<>(service.updateCorso(c), HttpStatus.OK);
	}
	
	@GetMapping("/docente/{id}")
	public ResponseEntity<?> findByDocente(@PathVariable Long id){
		return new ResponseEntity<>(service.findByDocente(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deleteCorso(id), HttpStatus.OK);
	}

}
