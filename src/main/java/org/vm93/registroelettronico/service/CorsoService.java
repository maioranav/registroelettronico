package org.vm93.registroelettronico.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.CorsoRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CorsoService {

	@Autowired CorsoRepository repo;
	@Autowired @Qualifier("fakeCorso") private ObjectProvider<Corso> corsofakeprov;
	
	public Page<Corso> getAllCorsi(Pageable pageable) {
		return (Page<Corso>) repo.findAll(pageable);
	}
	
	public void generaFakeCorso() {
		saveCorso(corsofakeprov.getObject());
	}
	
	public Corso saveCorso(Corso c) {
		if (c.getId() != null && repo.existsById(c.getId())) {
			throw new EntityExistsException("Non puoi salvare un id, utilizza il metodo PUT per modificare");
		}
		repo.save(c);
		return c;
	}
	public Corso updateCorso(Corso c) {
		if (c.getId() == null && !repo.existsById(c.getId())) {
			throw new EntityExistsException("Non puoi aggiornare questo Corso (non esiste o non hai fornito un id valido");
		}
		repo.save(c);
		return c;
	}
	
	public Corso findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("ID NON VALIDO");
		}
		return repo.findById(id).get();
	}
	
	public String deleteCorso(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		repo.deleteById(id);
		return "Corso eliminato dal DB";
	}
	
	public Corso getRandom() {
		return repo.getRandom();
	}
	
}
