package it.uniroma3.siw.demospring.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.demospring.model.Album;
import it.uniroma3.siw.demospring.model.Photo;
import it.uniroma3.siw.demospring.model.Photographer;
import it.uniroma3.siw.demospring.model.Request;
import it.uniroma3.siw.demospring.repository.AlbumRepository;
import it.uniroma3.siw.demospring.repository.PhotoRepository;
import it.uniroma3.siw.demospring.repository.PhotographerRepository;
import it.uniroma3.siw.demospring.repository.RequestRepository;

@Service
public class SilphService {

	@Autowired
	private RequestRepository rr;

	@Autowired
	private AlbumRepository ar;

	@Autowired 
	private PhotoRepository pr;

	@Autowired 
	private PhotographerRepository phr;


	@Transactional
	public void inserisciRichiesta(@Valid Request request) {
		this.rr.save(request);
	}

	@Transactional
	public List<Album> tuttiAlbum() {
		return (List<Album>) this.ar.findAll();
	}
	
	@Transactional
	public List<Request> tutteRequest() {
		return (List<Request>) this.rr.findAll();
	}

	public List<Photo> tutteFotoDaAlbumId(Long id) {
		Album alb = this.ar.findById(id).get();
		return alb.getPhotos();
	}

	public Photo PhotoById(Long id) {
		return this.pr.findById(id).get();
	}

	public Album AlbumById(Long id) {
		return this.ar.findById(id).get();
	}
	
	public Request RequestById(Long id) {
		return this.rr.findById(id).get();
	}

	public Boolean existPhotoById(Long id) {
		return this.pr.existsById(id);
	}
	
	public Boolean existAlbumById(Long id) {
		return this.ar.existsById(id);
	}
	
	public Boolean existPhotographerById(Long id) {
		return this.phr.existsById(id);
	}

	public List<Photo> PhotoByName(String name) {
		return this.pr.findByName(name);
	}

	public List<Photographer> PhotographerBySurname(String surname) {
		return this.phr.findBySurname(surname);
	}

	public List<Album> AlbumByName(String name) {
		return this.ar.findByName(name);
	}

	public Photographer PhotographerById(Long id) {
		return this.phr.findById(id).get();
	}

	public void inserisciPhoto(Photo photo) {
		this.pr.save(photo);
	}
	
	public void inserisciPhotographer(Photographer photographer) {
		this.phr.save(photographer);
	}
	
	public void inserisciAlbum(Album album) {
		this.ar.save(album);
	}

	public void modificaAlbum(Album al) {
		this.ar.deleteById(al.getId());
		this.ar.save(al);
	}
	

}
