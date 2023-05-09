package org.vm93.registroelettronico.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Plesso;
import org.vm93.registroelettronico.repo.PlessoRepository;

import jakarta.persistence.EntityExistsException;

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
	
	public Plesso getRandom() {
		return repo.getRandom();
	}
}
