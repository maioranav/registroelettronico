package org.vm93.registroelettronico.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Lezione;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.repo.CorsoRepository;
import org.vm93.registroelettronico.repo.LezioneRepo;
import org.vm93.registroelettronico.repo.StudenteRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LezioneService {
	
	@Autowired LezioneRepo repo;
	@Autowired CorsoRepository corsirepo;
	@Autowired StudenteRepository studenterepo;
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
		Lezione lezionedadb = repo.findById(c.getId()).get();
		List<Long> idPresenti = new ArrayList<>(); 
		c.getPresenze().forEach(el -> idPresenti.add(el.getId()));
		List<Studente> presenze = new ArrayList<>();
		idPresenti.forEach(el -> presenze.add(studenterepo.findById(el).get()));
		lezionedadb.setPresenze(presenze);
		repo.save(lezionedadb);
		return lezionedadb;
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
	
	public List<Lezione> getByCorsieData(List<Corso> c, LocalDate d) {
		LocalDate dcorr = d.minusDays(1);
		return (List<Lezione>) repo.searchByCorsoEDopoData(c, dcorr);
	}
	
	public List<Lezione> getByCorsieDataEsatta(List<Corso> c, LocalDate d) {
		return (List<Lezione>) repo.searchByCorsoEData(c, d);
	}
	
	public List<Lezione> getByDopoData(LocalDate d) {
		LocalDate dcorr = d.minusDays(1);
		return (List<Lezione>) repo.searchByDopoData(dcorr);
	}
	
	public List<Lezione> getByData(LocalDate d) {
		return (List<Lezione>) repo.searchByData(d);
	}
	
	public List<Lezione> getLast7Days() {
		LocalDate start = LocalDate.now().minusDays(1);
		LocalDate end = LocalDate.now().minusDays(8);
		return (List<Lezione>) repo.search7daysBefore(start, end);
	}
	
	public List<Lezione> getLast7DaysSpecific(List<Corso> c) {
		LocalDate start = LocalDate.now();
		LocalDate end = start.minusDays(7);
		return (List<Lezione>) repo.search7daysBeforeCorso(c, start, end);
	}
	
}
