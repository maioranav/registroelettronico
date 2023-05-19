package org.vm93.registroelettronico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Plesso;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.CorsoRepository;
import org.vm93.registroelettronico.repo.PlessoRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CorsoService {

	@Autowired CorsoRepository repo;
	@Autowired PlessoRepository plessorepo;
	@Autowired @Qualifier("fakeCorso") private ObjectProvider<Corso> corsofakeprov;
	
	public Page<Corso> getAllCorsi(Pageable pageable) {
		return (Page<Corso>) repo.findAll(pageable);
	}
	
	public List<Corso> getAllCorsi() {
		return (List<Corso>) repo.findAll();
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
		Plesso p = plessorepo.findById(c.getPlesso().getId()).get();
		Docente d = repo.findById(c.getId()).get().getDocente();
		c.setDocente(d);
		c.setPlesso(p);
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
