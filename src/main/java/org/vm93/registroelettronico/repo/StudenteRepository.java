package org.vm93.registroelettronico.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Studente;

public interface StudenteRepository extends CrudRepository<Studente, Long>, PagingAndSortingRepository<Studente, Long> {

	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
	public Studente findByEmail(String email);
	public Studente findByUsername(String username);
	
}
