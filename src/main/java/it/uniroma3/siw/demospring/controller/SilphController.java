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


	@RequestMapping("/admin_add")
	public String admin_add(Model model) {
		//prende i temporanei e li riporta
		return "admin_add.html";
	}

	@RequestMapping("/admin_search")
	public String admin_search(Model model) {
		//prende i temporanei e li riporta
		model.addAttribute("stringa", new Photo());
		return "admin_search.html";
	}

	@RequestMapping("/admin_requests")
	public String admin_requests(Model model) {
		//prende i temporanei e li riporta
		return "admin_requests.html";
	}

	@RequestMapping("/admin_homepage")
	public String admin_homepage(Model model) {
		//prende i temporanei e li riporta
		return "admin_homepage.html";
	}

	/*@RequestMapping(value="/submitPhotoForm", method = RequestMethod.POST)
	public String submitPhotoForm(@Valid @ModelAttribute("photo") Photo photo,
			Model model, BindingResult br, HttpSession session) {
		this.rv.validate(request, br);
		if(!br.hasErrors()) {
			request.setPhotos((List<Photo>)session.getAttribute("photos"));
			ss.inserisciRichiesta(request);
			List<Photo> photos = new ArrayList<Photo>();
			session.setAttribute("photos", photos);
			return "requested.html";
		}
		return "requestForm.html";
	}*/

	@RequestMapping(value="/searchPhoto", method = RequestMethod.POST)
	public String searchPhoto(@Valid @ModelAttribute("photo") Photo input,
			Model model) {
		model.addAttribute("photos", this.ss.PhotoByName(input.getName()));
		return "photos_found.html";
	}
	
	@RequestMapping(value="/searchPhotographer", method = RequestMethod.POST)
	public String searchPhotographer(@Valid @ModelAttribute("photographer") Photo input,
			Model model) {
		model.addAttribute("photographers", this.ss.PhotographerBySurname(input.getName()));
		return "photographers_found.html";
	}
	
	@RequestMapping(value="/searchAlbum", method = RequestMethod.POST)
	public String searchAlbum(@Valid @ModelAttribute("photographer") Photo input,
			Model model) {
		model.addAttribute("albums", this.ss.PhotographerBySurname(input.getName()));
		return "albums_found.html";
	}

	@RequestMapping("/RequestForm")
	public String requestForm(Model model) {
		model.addAttribute("request", new Request());
		return "requestForm.html";
	}


	@RequestMapping(value="/submitRequestForm", method = RequestMethod.POST)
	public String submitRequestForm(@Valid @ModelAttribute("request") Request request,
			Model model, BindingResult br, HttpSession session) {
		this.rv.validate(request, br);
		if(!br.hasErrors()) {
			request.setPhotos((List<Photo>)session.getAttribute("photos"));
			ss.inserisciRichiesta(request);
			List<Photo> photos = new ArrayList<Photo>();
			session.setAttribute("photos", photos);
			return "requested.html";
		}
		return "requestForm.html";
	}
}
