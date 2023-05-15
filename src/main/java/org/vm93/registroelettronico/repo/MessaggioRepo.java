package org.vm93.registroelettronico.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Lezione;
import org.vm93.registroelettronico.model.Messaggio;

public interface MessaggioRepo extends PagingAndSortingRepository<Messaggio, Long>, CrudRepository<Messaggio, Long> {
	
	Page<Messaggio> findAllByCorsoIn(List<Corso> corsi, Pageable pageable);

}
