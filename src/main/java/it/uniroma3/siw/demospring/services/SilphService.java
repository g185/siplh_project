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

	@Transactional
	public List<Photo> tutteFotoDaAlbumId(Long id) {
		Album alb = this.ar.findById(id).get();
		return alb.getPhotos();
	}

	@Transactional
	public Photo PhotoById(Long id) {
		return this.pr.findById(id).get();
	}

	@Transactional
	public Album AlbumById(Long id) {
		return this.ar.findById(id).get();
	}

	@Transactional
	public Request RequestById(Long id) {
		return this.rr.findById(id).get();
	}

	@Transactional
	public Boolean existPhotoById(Long id) {
		return this.pr.existsById(id);
	}

	@Transactional
	public Boolean existAlbumById(Long id) {
		return this.ar.existsById(id);
	}

	@Transactional
	public Boolean existPhotographerById(Long id) {
		return this.phr.existsById(id);
	}

	@Transactional
	public List<Photo> PhotoByName(String name) {
		return this.pr.findByName(name);
	}

	@Transactional
	public List<Photographer> PhotographerBySurname(String surname) {
		return this.phr.findBySurname(surname);
	}

	@Transactional
	public List<Album> AlbumByName(String name) {
		return this.ar.findByName(name);
	}

	@Transactional
	public Photographer PhotographerById(Long id) {
		return this.phr.findById(id).get();
	}

	@Transactional
	public void inserisciPhoto(Photo photo) {
		this.pr.save(photo);
	}

	@Transactional
	public void inserisciPhotographer(Photographer photographer) {
		this.phr.save(photographer);
	}

	@Transactional
	public void inserisciAlbum(Album album) {
		this.ar.save(album);
	}

	@Transactional
	public void modificaAlbum(Album al) {
		this.ar.save(al);
	}

	public List<Photographer> tuttiFotografi() {
		return (List<Photographer>) this.phr.findAll();
	}
	

}
