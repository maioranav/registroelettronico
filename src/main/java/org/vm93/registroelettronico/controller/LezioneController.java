package org.vm93.registroelettronico.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vm93.registroelettronico.auth.entity.User;
import org.vm93.registroelettronico.auth.repository.UserRepository;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Lezione;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.service.CorsoService;
import org.vm93.registroelettronico.service.LezioneService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/lezioni")
public class LezioneController {
	
	@Autowired LezioneService service;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll(Pageable pageable) {
		
		return new ResponseEntity<>(service.getAllLezioni(pageable), HttpStatus.OK);
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
	
	@PostMapping("/calendario/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getByCorsieData(@RequestBody List<Corso> c, @PathVariable LocalDate data) {
		return new ResponseEntity<>(service.getByCorsieData(c, data), HttpStatus.OK);
	}
	
	@GetMapping("/after/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getByDopoData(@PathVariable LocalDate data) {
		return new ResponseEntity<>(service.getByDopoData(data), HttpStatus.OK);
	}
	
	@GetMapping("/data/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getByData(@PathVariable LocalDate data) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User usercomplete = userRepo.findByEmail(auth.getName()).get();
		if (usercomplete.getClass().getSimpleName().equals("Docente")) {
			return new ResponseEntity<>(service.getByCorsieDataEsatta(((Docente) usercomplete).getCorsi(), data), HttpStatus.OK);
		}
		if (usercomplete.getClass().getSimpleName().equals("Studente")) {
			return new ResponseEntity<>(service.getByCorsieDataEsatta(((Studente) usercomplete).getCorsi(), data), HttpStatus.OK);
		}
		return new ResponseEntity<>(service.getByData(data), HttpStatus.OK);
	}
	
	@GetMapping("/last7")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getLast7() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User usercomplete = userRepo.findByEmail(auth.getName()).get();
		if (usercomplete.getClass().getSimpleName().equals("Docente")) {
			return new ResponseEntity<>(service.getLast7DaysSpecific(((Docente) usercomplete).getCorsi()), HttpStatus.OK);
		}
		if (usercomplete.getClass().getSimpleName().equals("Studente")) {
			return new ResponseEntity<>(service.getLast7DaysSpecific(((Studente) usercomplete).getCorsi() ), HttpStatus.OK);
		}
		return new ResponseEntity<>(service.getLast7Days(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> postCorso(@RequestBody Lezione c){
		return new ResponseEntity<>(service.saveLezione(c), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> putCorso(@RequestBody Lezione c){
		return new ResponseEntity<>(service.updateLezione(c), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deleteLezione(id), HttpStatus.OK);
	}

}
