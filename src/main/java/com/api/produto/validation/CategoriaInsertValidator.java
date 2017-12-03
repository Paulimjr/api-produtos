package com.api.produto.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.produto.dto.CategoriaDTO;
import com.api.produto.services.exceptions.FieldMessage;


public class CategoriaInsertValidator implements ConstraintValidator<CategoriaInsert, CategoriaDTO> {

	@Override
	public void initialize(CategoriaInsert constraintAnnotation) {		
	}

	@Override
	public boolean isValid(CategoriaDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (list != null && org.springframework.util.StringUtils.isEmpty(value.getNome())) {
			list.add(new FieldMessage("nome", "Nome não pode ser vazio."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		
		return false;
	}

}
