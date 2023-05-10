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
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.CorsoRepository;
import org.vm93.registroelettronico.repo.StudenteRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
@Service
public class StudenteService {

	@Autowired StudenteRepository repo;
	@Autowired CorsoRepository corsirepo;
	
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
	
	public Studente updateStudente(Studente d) {
		if (d.getId() == null && !repo.existsById(d.getId())) {
			throw new EntityExistsException("Non puoi aggiornare questo Studente (non esiste o non hai fornito un id valido.)");
		}
		repo.save(d);
		return d;
	}
	
	public Studente getRandom() {
		return repo.getRandom();
	}
	
	public Studente findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("ID NON VALIDO");
		}
		return repo.findById(id).get();
	}
	
	public String deleteStudente(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		repo.deleteById(id);
		return "Studente eliminato dal DB";
	}
	
	public List<Studente> getByCorsi(Long id) {
		if (!corsirepo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		Corso c = corsirepo.findById(id).get();
		List<Corso> list = new ArrayList<>();
		list.add(c);
		return (List<Studente>) repo.findAllByCorsiIn(list);
	}

	
}
