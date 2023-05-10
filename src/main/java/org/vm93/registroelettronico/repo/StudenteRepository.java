package org.vm93.registroelettronico.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Studente;

public interface StudenteRepository extends CrudRepository<Studente, Long>, PagingAndSortingRepository<Studente, Long> {

	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
	public Studente findByEmail(String email);
	public Studente findByUsername(String username);
	
	@Query("SELECT s FROM Studente s ORDER BY RANDOM() LIMIT 1")
	public Studente getRandom();
	
	@Query("SELECT s FROM Studente s WHERE s.corsi = :corso")
	List<Studente> getByCorso(@Param("corso") Corso c);
	
	List<Studente> findAllByCorsiIn(List<Corso> corsi);
}
