package org.vm93.registroelettronico.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Messaggio;

public interface MessaggioRepo extends PagingAndSortingRepository<Messaggio, Long>, CrudRepository<Messaggio, Long> {

}
