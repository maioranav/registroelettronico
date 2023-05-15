package org.vm93.registroelettronico.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Lezione;
import org.vm93.registroelettronico.model.Studente;

public interface LezioneRepo extends PagingAndSortingRepository<Lezione, Long>, CrudRepository<Lezione, Long> {

	@Query("SELECT l FROM Lezione l ORDER BY RANDOM() LIMIT 1")
	public Lezione getRandom();
	
	List<Lezione> findAllByCorsoIn(List<Corso> corsi);
}
