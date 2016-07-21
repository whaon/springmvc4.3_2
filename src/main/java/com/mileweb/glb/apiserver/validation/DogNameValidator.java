package com.mileweb.glb.apiserver.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

public class DogNameValidator  implements ConstraintValidator<DogNameDuplicate, String> {
	
	String[] initNames;

	@Override
	public void initialize(DogNameDuplicate constraintAnnotation) {
		initNames = constraintAnnotation.init();
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(ArrayUtils.contains(initNames, value)) {
			return false;
		}
		return true;
	}

}
