package it.uniroma3.siw.demospring.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.demospring.model.Request;
import it.uniroma3.siw.demospring.repository.RequestRepository;

@Service
public class SilphService {

	@Autowired
	private RequestRepository rr;
	
	public void inserisciRichiesta(@Valid Request request) {
		this.rr.save(request);
		
	}

}
