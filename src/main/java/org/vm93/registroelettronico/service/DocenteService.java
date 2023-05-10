package org.vm93.registroelettronico.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.DocenteRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class DocenteService {
	
	@Autowired DocenteRepository repo;
	
	@Autowired @Qualifier("fakeDocente") private ObjectProvider<Docente> docentefakeprov;
	
	public Page<Docente> getAllDocenti(Pageable pageable) {
		return (Page<Docente>) repo.findAll(pageable);
	}
	
	public void generaFakeDocente() {
		saveDocente(docentefakeprov.getObject());
	}
	
	public Docente saveDocente(Docente d) {
		if (repo.existsByEmail(d.getEmail()) || repo.existsByUsername(d.getUsername())) {
			throw new EntityExistsException("Username o Email già esistenti");
		}
		if (d.getId() != null && repo.existsById(d.getId())) {
			throw new EntityExistsException("Non puoi salvere un id, utilizza il metodo PUT per modificare");
		}
		PasswordEncoder pe = new BCryptPasswordEncoder();
		d.setPassword(pe.encode(d.getPassword()));
		repo.save(d);
		return d;
	}
	public Docente updateDocente(Docente d) {
		if (d.getId() == null && !repo.existsById(d.getId())) {
			throw new EntityExistsException("Non puoi aggiornare questo Docente (non esiste o non hai fornito un id valido");
		}
		if (d.getPassword() == null) {
			throw new EntityExistsException("Non puoi aggiornare questo Studente se non fornisci una password");
		}
		PasswordEncoder pe = new BCryptPasswordEncoder();
		d.setPassword(pe.encode(d.getPassword()));
		repo.save(d);
		return d;
	}
	
	public Docente getRandom() {
		return repo.getRandom();
	}
	
	public Docente findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("ID NON VALIDO");
		}
		return repo.findById(id).get();
	}
	
	public String deleteDocente(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		repo.deleteById(id);
		return "Docente eliminato dal DB";
	}

}
