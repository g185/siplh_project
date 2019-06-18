package it.uniroma3.siw.demospring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.demospring.model.Photo;
import it.uniroma3.siw.demospring.model.Request;
import it.uniroma3.siw.demospring.services.RequestValidator;
import it.uniroma3.siw.demospring.services.SilphService;

//Controller MVC della webapp
@Controller
@SessionAttributes("photosNumber")
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
	public String gallery(Model model, HttpSession session) {
		if(session.getAttribute("photos") == null) {
			List<Photo> photos = new ArrayList<Photo>();
			session.setAttribute("photos", photos);
		}
		model.addAttribute("albums", this.ss.tuttiAlbum());
		model.addAttribute("photosNumber", ((List<Photo>)session.getAttribute("photos")).size());
		return "gallery.html";
	}

	@RequestMapping("/album/{id}")
	public String album(@PathVariable("id") Long id, Model model, HttpSession session)
	{ 
		if(id != null) {
			model.addAttribute("photos", this.ss.tutteFotoDaAlbumId(id));
			model.addAttribute("album", this.ss.AlbumById(id));
			return "photos.html";

		}else {model.addAttribute("albums", this.ss.tuttiAlbum());
		return "gallery.html";
		}
	}

	@RequestMapping(value="/album/{idAlbum}/addPhoto/{idPhoto}")
	public String addPhoto(@PathVariable("idAlbum") Long idA,
			@PathVariable("idPhoto") Long id, Model model, HttpSession session)
	{	
		Boolean contained = false;
		List<Photo> photos = (List<Photo>) session.getAttribute("photos");
		for(Photo ph : photos){
			if (ph.getId().equals(id)) {
				contained = true;
			}
		}
		if(!contained)
		{	
			photos.add(this.ss.PhotoById(id));
			session.setAttribute("photos", photos);
			model.addAttribute("photosNumber", ((List<Photo>)session.getAttribute("photos")).size());
			
		}
		model.addAttribute("album", this.ss.AlbumById(idA));
		model.addAttribute("photos", this.ss.tutteFotoDaAlbumId(idA));
		return "photos.html";
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
			Model model, BindingResult br, HttpSession session) {
		this.rv.validate(request, br);
		if(!br.hasErrors()) {
			request.setPhotos((List<Photo>)session.getAttribute("photos"));
			ss.inserisciRichiesta(request);
			return "requested.html";
		}
		return "requestForm.html";
	}
}
