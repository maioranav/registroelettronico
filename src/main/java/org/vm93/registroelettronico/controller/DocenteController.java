package org.vm93.registroelettronico.controller;

import java.util.List;

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
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.service.DocenteService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/docenti")
public class DocenteController {

	@Autowired DocenteService service;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll(Pageable pageable) {
		return new ResponseEntity<Page<Docente>>(service.getAllDocenti(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<Docente>>(service.getAllDocenti(), HttpStatus.OK);
	}
	
	@GetMapping("/random")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getRandom() {
		return new ResponseEntity<>(service.getRandom(), HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getRandom(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> postDocente(@RequestBody Docente d){
		return new ResponseEntity<>(service.saveDocente(d), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> putDocente(@RequestBody Docente d){
		return new ResponseEntity<>(service.updateDocente(d), HttpStatus.OK);
	}
	
	@DeleteMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deleteDocente(id), HttpStatus.OK);
	}
	
}
