package org.vm93.registroelettronico.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.repo.DocenteRepository;

import jakarta.persistence.EntityExistsException;


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
		repo.save(d);
		return d;
	}

}
