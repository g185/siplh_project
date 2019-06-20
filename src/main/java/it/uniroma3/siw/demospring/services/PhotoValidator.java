package it.uniroma3.siw.demospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.demospring.model.Photo;
import it.uniroma3.siw.demospring.model.Photographer;

@Component
public class PhotoValidator implements Validator{

	@Autowired
	private SilphService ss;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Photo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imagePath", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phId", "required");
		if(errors.hasErrors()) return;
		if(!this.ss.existPhotographerById(Long.parseLong(((Photo) target).getPhId(), 10))) {
			Photo ph = (Photo)target;
			ph.setPhId("");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phId", "required");
		}
		
	}

}
