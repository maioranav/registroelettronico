package org.vm93.registroelettronico.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Docente;

public interface CorsoRepository extends PagingAndSortingRepository<Corso, Long>, CrudRepository<Corso, Long> {

	
	@Query("SELECT c FROM Corso c ORDER BY RANDOM() LIMIT 1")
	public Corso getRandom();
	
	public List<Corso> findByDocente(Docente docente);
	
}
