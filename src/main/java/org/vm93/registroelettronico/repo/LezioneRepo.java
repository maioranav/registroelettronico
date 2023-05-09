package org.vm93.registroelettronico.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Lezione;

public interface LezioneRepo extends PagingAndSortingRepository<Lezione, Long>, CrudRepository<Lezione, Long> {

}
