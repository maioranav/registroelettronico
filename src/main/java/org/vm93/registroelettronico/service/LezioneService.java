package org.vm93.registroelettronico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Lezione;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.CorsoRepository;
import org.vm93.registroelettronico.repo.LezioneRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LezioneService {
	
	@Autowired LezioneRepo repo;
	@Autowired CorsoRepository corsirepo;
	@Autowired @Qualifier("lezioneFake") private ObjectProvider<Lezione> lezionefakeprov;
	
	public Page<Lezione> getAllLezioni(Pageable pageable) {
		return (Page<Lezione>) repo.findAll(pageable);
	}
	
	public void generaFakeLezione() {
		saveLezione(lezionefakeprov.getObject());
	}
	
	public Lezione saveLezione(Lezione c) {
		if (c.getId() != null && repo.existsById(c.getId())) {
			throw new EntityExistsException("Non puoi salvare un id, utilizza il metodo PUT per modificare");
		}
		repo.save(c);
		return c;
	}
	public Lezione updateLezione(Lezione c) {
		if (c.getId() == null && !repo.existsById(c.getId())) {
			throw new EntityExistsException("Non puoi aggiornare questa Lezione (non esiste o non hai fornito un id valido");
		}
		repo.save(c);
		return c;
	}
	
	public Lezione findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("ID NON VALIDO");
		}
		return repo.findById(id).get();
	}
	
	public String deleteLezione(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		repo.deleteById(id);
		return "Lezione eliminato dal DB";
	}
	
	public Lezione getRandom() {
		return repo.getRandom();
	}
	
	public List<Lezione> getByCorsi(Long id) {
		if (!corsirepo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		Corso c = corsirepo.findById(id).get();
		List<Corso> list = new ArrayList<>();
		list.add(c);
		return (List<Lezione>) repo.findAllByCorsoIn(list);
	}
	
}
