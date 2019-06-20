package it.uniroma3.siw.demospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.demospring.model.Album;

@Component
public class AlbumValidator implements Validator{

	@Autowired
	private SilphService ss;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Album.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imagePath", "required");
	}

}
