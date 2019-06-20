package it.uniroma3.siw.demospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.demospring.model.AlbumPhoto;

@Component
public class AlbumPhotoValidator implements Validator{

	@Autowired
	private SilphService ss;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumPhoto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idAlbum", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPhoto", "required");
		if(errors.hasErrors()) return;
		if(!this.ss.existAlbumById(Long.parseLong(((AlbumPhoto) target).getIdAlbum(), 10))) {
			AlbumPhoto ap = (AlbumPhoto)target;
			ap.setIdAlbum("");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idAlbum", "required");
		}
		if(errors.hasErrors()) return;
		if(!this.ss.existPhotoById(Long.parseLong(((AlbumPhoto) target).getIdPhoto(), 10))) {
			AlbumPhoto ap = (AlbumPhoto)target;
			ap.setIdPhoto("");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPhoto", "required");
		}
	}
	

}
