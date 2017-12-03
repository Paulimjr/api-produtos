package com.api.produto.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.produto.dto.ProdutoDTO;
import com.api.produto.services.exceptions.FieldMessage;


public class ProdutoInsertValidator implements ConstraintValidator<ProdutoInsert, ProdutoDTO> {

	@Override
	public void initialize(ProdutoInsert constraintAnnotation) {		
	}

	@Override
	public boolean isValid(ProdutoDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (value.getNome().isEmpty()) {
			list.add(new FieldMessage("nome", "Nome não pode ser vázio."));
		}
		
		if (value.getPreco() < 0) {
			list.add(new FieldMessage("preco", "Preço não pode ser menor que zero."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		
		return false;
	}

}
