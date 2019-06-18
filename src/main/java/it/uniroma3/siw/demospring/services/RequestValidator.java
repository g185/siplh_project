package it.uniroma3.siw.demospring.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.demospring.model.Request;
@Component
public class RequestValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Request.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerSurname", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerEmail", "required");	
	}

}
