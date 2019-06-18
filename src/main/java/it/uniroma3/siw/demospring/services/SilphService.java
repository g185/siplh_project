package it.uniroma3.siw.demospring.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.demospring.model.Album;
import it.uniroma3.siw.demospring.model.Photo;
import it.uniroma3.siw.demospring.model.Request;
import it.uniroma3.siw.demospring.repository.AlbumRepository;
import it.uniroma3.siw.demospring.repository.PhotoRepository;
import it.uniroma3.siw.demospring.repository.RequestRepository;

@Service
public class SilphService {

	@Autowired
	private RequestRepository rr;
	
	@Autowired
	private AlbumRepository ar;
	
	@Autowired 
	private PhotoRepository pr;
	
	
	@Transactional
	public void inserisciRichiesta(@Valid Request request) {
		this.rr.save(request);
		
	}

	@Transactional
	public List<Album> tuttiAlbum() {
		return (List<Album>) this.ar.findAll();
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

	public Boolean existPhotoById(Long id) {
		return this.pr.existsById(id);
	}

}
