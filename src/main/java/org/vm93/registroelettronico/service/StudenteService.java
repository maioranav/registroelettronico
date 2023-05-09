package org.vm93.registroelettronico.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.StudenteRepository;

import jakarta.persistence.EntityExistsException;
@Service
public class StudenteService {

	@Autowired StudenteRepository repo;
	
	@Autowired @Qualifier("fakeStudente") private ObjectProvider<Studente> studentefakeprov;
	
	public Page<Studente> getAllStudenti(Pageable pageable) {
		return (Page<Studente>) repo.findAll(pageable);
	}
	
	public void generaFakeStudente() {
		saveStudente(studentefakeprov.getObject());
	}
	
	public Studente saveStudente(Studente d) {
		if (repo.existsByEmail(d.getEmail()) || repo.existsByUsername(d.getUsername())) {
			throw new EntityExistsException("Username o Email già esistenti");
		}
		if (d.getId() != null && repo.existsById(d.getId())) {
			throw new EntityExistsException("Non puoi salvere un id, utilizza il metodo PUT per modificare");
		}
		repo.save(d);
		return d;
	}

	
}
