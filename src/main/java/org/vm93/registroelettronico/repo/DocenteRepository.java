package org.vm93.registroelettronico.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Docente;

public interface DocenteRepository extends CrudRepository<Docente, Long>, PagingAndSortingRepository<Docente, Long> {
	
	public Page<Docente> findById(Object id, Pageable pageable);
	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
	public Docente findByEmail(String email);
	public Docente findByUsername(String username);

}
