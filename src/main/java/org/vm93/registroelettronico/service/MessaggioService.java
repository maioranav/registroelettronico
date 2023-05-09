package org.vm93.registroelettronico.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Messaggio;
import org.vm93.registroelettronico.repo.MessaggioRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MessaggioService {

	
	@Autowired MessaggioRepo repo;
	@Autowired @Qualifier("fakeMessaggio") private ObjectProvider<Messaggio> msgfakeprov;
	
	public Page<Messaggio> getAllMessaggio(Pageable pageable) {
		return (Page<Messaggio>) repo.findAll(pageable);
	}
	
	public void generaFakeMessaggio() {
		saveMessaggio(msgfakeprov.getObject());
	}
	
	public Messaggio saveMessaggio(Messaggio c) {
		if (c.getId() != null && repo.existsById(c.getId())) {
			throw new EntityExistsException("Non puoi salvare un id, utilizza il metodo PUT per modificare");
		}
		repo.save(c);
		return c;
	}
	public Messaggio updateMessaggio(Messaggio c) {
		if (c.getId() == null && !repo.existsById(c.getId())) {
			throw new EntityExistsException("Non puoi aggiornare questo Messaggio (non esiste o non hai fornito un id valido");
		}
		repo.save(c);
		return c;
	}
	
	public Messaggio findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("ID NON VALIDO");
		}
		return repo.findById(id).get();
	}
	
	public String deleteMessaggio(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		repo.deleteById(id);
		return "Corso eliminato dal DB";
	}
	
}
