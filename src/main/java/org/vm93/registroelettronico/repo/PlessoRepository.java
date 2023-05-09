package org.vm93.registroelettronico.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vm93.registroelettronico.model.Plesso;

public interface PlessoRepository extends CrudRepository<Plesso, Long>, PagingAndSortingRepository<Plesso, Long> {

}
