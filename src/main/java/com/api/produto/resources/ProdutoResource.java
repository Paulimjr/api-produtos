package com.api.produto.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.produto.services.ProdutoService;

/**
 * Controlar as requisições de produtos
 * 
 * @author paulo
 *
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;

}
