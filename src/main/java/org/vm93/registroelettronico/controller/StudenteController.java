package org.vm93.registroelettronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.service.StudenteService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/studenti")
public class StudenteController {
	
	@Autowired StudenteService service;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll(Pageable pageable) {
		return new ResponseEntity<Page<Studente>>(service.getAllStudenti(pageable), HttpStatus.OK);
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
	@GetMapping("/corsi/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getByCorsi(@PathVariable Long id) {
		return new ResponseEntity<>(service.getByCorsi(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> postStudente(@RequestBody Studente s){
		return new ResponseEntity<>(service.saveStudente(s), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> putStudente(@RequestBody Studente s){
		return new ResponseEntity<>(service.updateStudente(s), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deleteStudente(id), HttpStatus.OK);
	}
}
