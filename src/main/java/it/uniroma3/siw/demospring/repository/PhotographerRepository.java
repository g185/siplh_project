package it.uniroma3.siw.demospring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.demospring.model.Photographer;

public interface PhotographerRepository extends CrudRepository<Photographer, Long>{

	List<Photographer> findByNameAndSurname(String name, String surname);

	List<Photographer> findBySurname(String surname);

}
