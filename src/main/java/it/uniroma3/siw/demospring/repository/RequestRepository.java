package it.uniroma3.siw.demospring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.demospring.model.Request;

public interface RequestRepository extends CrudRepository<Request, Long>{

}
