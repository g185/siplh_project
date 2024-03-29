package it.uniroma3.siw.demospring.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.demospring.model.Album;
import it.uniroma3.siw.demospring.model.AlbumPhoto;
import it.uniroma3.siw.demospring.model.Photo;
import it.uniroma3.siw.demospring.model.Photographer;
import it.uniroma3.siw.demospring.model.Request;
import it.uniroma3.siw.demospring.services.AlbumPhotoValidator;
import it.uniroma3.siw.demospring.services.AlbumValidator;
import it.uniroma3.siw.demospring.services.PhotoValidator;
import it.uniroma3.siw.demospring.services.PhotographerValidator;
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

	@Autowired
	private PhotoValidator pv;
	
	@Autowired
	private AlbumValidator av;
	
	@Autowired
	private PhotographerValidator phv;	
	
	@Autowired
	private AlbumPhotoValidator apv;
	
	private Principal principal;
	
	@RequestMapping("/")
	public String homepage() {
		return "homepage.html";
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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
	public String login(Model model, Principal principal) {
		this.principal=principal;
		model.addAttribute("nome", getAdminName());
		return "admin_homepage.html";
	}

	private String getAdminName() {
		String [] words = this.principal.toString().split(",");
		for(String w : words) {
			if(w.contains(" name="))
				return w.substring(6);
		}
		return "";
	}

	@RequestMapping("/admin_add")
	public String admin_add(Model model) {
		//prende i temporanei e li riporta
		model.addAttribute("nome", getAdminName());
		return "admin_add.html";
	}

	@RequestMapping("/admin_search")
	public String admin_search(Model model) {
		//prende i temporanei e li riporta
		model.addAttribute("nome", getAdminName());
		model.addAttribute("stringa", new Photo());
		return "admin_search.html";
	}

	@RequestMapping("/admin_requests")
	public String admin_requests(Model model) {
		//prende i temporanei e li riporta
		model.addAttribute("nome", getAdminName());
		model.addAttribute("requests", this.ss.tutteRequest());
		return "admin_requests.html";
	}

	@RequestMapping("/admin_homepage")
	public String admin_homepage(Model model, Principal principal) {
		model.addAttribute("nome", getAdminName());
		return "admin_homepage.html";
	}

	@RequestMapping(value="/submitPhotoForm", method = RequestMethod.POST)
	public String submitPhotoForm(@Valid @ModelAttribute("photo") Photo photo,
			Model model, BindingResult br) {
		model.addAttribute("nome", getAdminName());
		this.pv.validate(photo, br);
		if(!br.hasErrors()) {
			Photographer ph = this.ss.PhotographerById(Long.parseLong(photo.getPhId(), 10));
			photo.setPhotographer(ph);
			this.ss.inserisciPhoto(photo);
			return "admin_succeded.html";
		}
		return "admin_add_photo.html";
	}

	@RequestMapping(value="/submitPhotographerForm", method = RequestMethod.POST)
	public String submitPhotoForm(@Valid @ModelAttribute("photographer") Photographer photographer,
			Model model, BindingResult br) {
		model.addAttribute("nome", getAdminName());
		this.phv.validate(photographer, br);
		if(!br.hasErrors()) {
			this.ss.inserisciPhotographer(photographer);
			return "admin_succeded.html";
		}
		return "admin_add_photographer.html";
	}
	
	@RequestMapping(value="/submitAlbumForm", method = RequestMethod.POST)
	public String submitAlbumForm(@Valid @ModelAttribute("album") Album album,
			Model model, BindingResult br) {
		model.addAttribute("nome", getAdminName());
		this.av.validate(album, br);
		if(!br.hasErrors()) {
			this.ss.inserisciAlbum(album);
			return "admin_succeded.html";
		}
		return "admin_add_album.html";
	}

	@RequestMapping(value="/searchPhoto", method = RequestMethod.POST)
	public String searchPhoto(@Valid @ModelAttribute("photo") Photo input,
			Model model) {
		model.addAttribute("nome", getAdminName());
		model.addAttribute("photos", this.ss.PhotoByName(input.getName()));
		return "photos_found.html";
	}
	
	@RequestMapping(value="/searchPhotographer", method = RequestMethod.POST)
	public String searchPhotographer(@Valid @ModelAttribute("photographer") Photo input,
			Model model) {
		model.addAttribute("nome", getAdminName());
		model.addAttribute("photographers", this.ss.PhotographerBySurname(input.getName()));
		return "photographers_found.html";
	}
	
	@RequestMapping(value="/searchAlbum", method = RequestMethod.POST)
	public String searchAlbum(@Valid @ModelAttribute("album") Photo input,
			Model model) {
		model.addAttribute("nome", getAdminName());
		model.addAttribute("albums", this.ss.AlbumByName(input.getName()));
		return "albums_found.html";
	}

	@RequestMapping("/RequestForm")
	public String requestForm(Model model) {
		model.addAttribute("request", new Request());
		return "requestForm.html";
	}


	@SuppressWarnings("unchecked")
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
	
	@RequestMapping(value="/submitAlbumPopulationForm", method = RequestMethod.POST)
	public String submitAlbumPopulationForm(@Valid @ModelAttribute("albumPhoto") AlbumPhoto albumPhoto,
			Model model, BindingResult br, HttpSession session) {
		model.addAttribute("nome", getAdminName());
		this.apv.validate(albumPhoto, br);
		if(!br.hasErrors()) {
			Album al = this.ss.AlbumById(Long.parseLong(albumPhoto.getIdAlbum()));
			List<Photo> photos = al.getPhotos();
			photos.add(this.ss.PhotoById(Long.parseLong(albumPhoto.getIdPhoto())));
			al.setPhotos(photos);
			this.ss.modificaAlbum(al);
			return "admin_succeded.html";
		}
		return "populate_album.html";
	}
	
	@RequestMapping("/addPhotoForm")
	public String addPhotoForm(Model model) {
		model.addAttribute("photographers", this.ss.tuttiFotografi());
		model.addAttribute("nome", getAdminName());
		model.addAttribute("photo", new Photo());
		return "admin_add_photo.html";
	}

	@RequestMapping("/addPhotographerForm")
	public String addPhotographerForm(Model model) {
		model.addAttribute("nome", getAdminName());
		model.addAttribute("photographer", new Photographer());
		return "admin_add_photographer.html";
	}
	
	@RequestMapping("/addAlbumForm")
	public String addAlbumForm(Model model) {
		model.addAttribute("nome", getAdminName());
		model.addAttribute("album", new Album());
		return "admin_add_album.html";
	}
	
	@RequestMapping("/populateAlbumForm")
	public String populateAlbumForm(Model model) {
		model.addAttribute("photos", this.ss.tutteFoto());
		model.addAttribute("albums", this.ss.tuttiAlbum());
		model.addAttribute("nome", getAdminName());
		model.addAttribute("albumPhoto", new AlbumPhoto());
		return "populate_album.html";
	}
	
	@RequestMapping("/request/{id}")
	public String request(@PathVariable("id") Long id, Model model, HttpSession session)
	{ 
		model.addAttribute("nome", getAdminName());
		if(id != null) {
			model.addAttribute("request", this.ss.RequestById(id));
			return "request.html";

		}else {model.addAttribute("requests", this.ss.tutteRequest());
		return "admin_requests.html";
		}
	}
}
