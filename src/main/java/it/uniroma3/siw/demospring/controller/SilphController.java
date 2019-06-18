package it.uniroma3.siw.demospring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.demospring.model.Request;
import it.uniroma3.siw.demospring.services.RequestValidator;
import it.uniroma3.siw.demospring.services.SilphService;

//Controller MVC della webapp
@Controller
public class SilphController {

	@Autowired
	private SilphService ss;
	

	@Autowired
	private RequestValidator rv;
	
	@RequestMapping("/")
	public String homepage() {
		return "homepage.html";
	}
	
	@RequestMapping("/Gallery")
	public String gallery(Model model) {
		model.addAttribute("albums", this.ss.tuttiAlbum());
		return "gallery.html";
	}
	

	
	@RequestMapping("/Login")
	public String login(Model model) {
		//prende i temporanei e li riporta
		return "login.html";
	}
	
	@RequestMapping("/RequestForm")
	public String requestForm(Model model) {
		model.addAttribute("request", new Request());
		return "requestForm.html";
	}
	
	
	@RequestMapping(value="/submitForm", method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute("request") Request request,
			Model model, BindingResult br) {
		this.rv.validate(request, br);
		if(!br.hasErrors()) {
			ss.inserisciRichiesta(request);
			return "requested.html";
		}
		return "requestForm.html";
	}
}
