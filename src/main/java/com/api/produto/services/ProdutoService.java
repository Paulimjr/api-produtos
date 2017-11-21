package com.api.produto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.produto.repository.ProdutoRepository;

/**
 * Controlar serviços relacionado a produto
 * 
 * @author paulo
 *
 */
@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	

}
