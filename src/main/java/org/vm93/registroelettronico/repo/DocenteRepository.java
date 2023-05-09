package org.vm93.registroelettronico.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Docente;

public interface DocenteRepository extends CrudRepository<Docente, Long>, PagingAndSortingRepository<Docente, Long> {
	
	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
	public Docente findByEmail(String email);
	public Docente findByUsername(String username);
	
	@Query("SELECT d FROM Docente d ORDER BY RANDOM() LIMIT 1")
	public Docente getRandom();

}
