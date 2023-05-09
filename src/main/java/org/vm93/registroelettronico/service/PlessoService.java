package org.vm93.registroelettronico.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Plesso;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.PlessoRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PlessoService {
	@Autowired
	PlessoRepository repo;

	@Autowired
	@Qualifier("fakePlesso")
	private ObjectProvider<Plesso> plessofakeprov;

	public Page<Plesso> getAllPlessi(Pageable pageable) {
		return (Page<Plesso>) repo.findAll(pageable);
	}

	public void generaFakePlesso() {
		savePlesso(plessofakeprov.getObject());
	}

	public Plesso savePlesso(Plesso d) {
		if (d.getId() != null && repo.existsById(d.getId())) {
			throw new EntityExistsException("Non puoi salvere un id, utilizza il metodo PUT per modificare");
		}
		repo.save(d);
		return d;
	}
	public Plesso updatePlesso(Plesso d) {
		if (d.getId() == null && !repo.existsById(d.getId())) {
			throw new EntityExistsException("Non puoi aggiornare questo Plesso (non esiste o non hai fornito un id valido");
		}
		repo.save(d);
		return d;
	}
	
	public Plesso getRandom() {
		return repo.getRandom();
	}
	
	public Plesso findById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("ID NON VALIDO");
		}
		return repo.findById(id).get();
	}
	
	public String deletePlesso(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("L'id inserito non esiste");
		}
		repo.deleteById(id);
		return "Plesso eliminato dal DB";
	}
}
