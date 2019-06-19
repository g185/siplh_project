package it.uniroma3.siw.demospring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.demospring.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long>{

	List<Photo> findByName(String name);

}
