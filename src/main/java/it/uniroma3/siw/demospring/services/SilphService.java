package it.uniroma3.siw.demospring.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.demospring.model.Album;
import it.uniroma3.siw.demospring.model.Request;
import it.uniroma3.siw.demospring.repository.AlbumRepository;
import it.uniroma3.siw.demospring.repository.RequestRepository;

@Service
public class SilphService {

	@Autowired
	private RequestRepository rr;
	
	@Autowired
	private AlbumRepository ar;
	
	@Transactional
	public void inserisciRichiesta(@Valid Request request) {
		this.rr.save(request);
		
	}

	@Transactional
	public List<Album> tuttiAlbum() {
		return (List<Album>) this.ar.findAll();
	}

}
