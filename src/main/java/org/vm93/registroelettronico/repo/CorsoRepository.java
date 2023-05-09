package org.vm93.registroelettronico.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Corso;

public interface CorsoRepository extends PagingAndSortingRepository<Corso, Long>, CrudRepository<Corso, Long> {

}
