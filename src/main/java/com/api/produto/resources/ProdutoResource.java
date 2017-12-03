package com.api.produto.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.produto.dto.ProdutoDTO;
import com.api.produto.entity.Produto;
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
	
	/**
	 * API para consultar um produto pelo identificador
	 * 
	 * @author paulo
	 * @param id o identificador do produto
	 * @return a produto
	 */
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarProdutoPeloId(@Valid @PathVariable final Integer id) {
		Produto prd = this.produtoService.buscarProduto(id);
		return  ResponseEntity.ok().body(prd);
	}
	
	/**
	 * API para inserir um novo prodouto
	 * 
	 * @param prd produto para cadastrar
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ProdutoDTO prd) {
		
		Produto produto = produtoService.fromDTO(prd);
		produto = produtoService.novoProduto(produto);
		
 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
 				.path("/{id}").buildAndExpand(prd.getId()).toUri();
	 	return ResponseEntity.created(uri).build();
	}
	
	/**
	 * API para alterar um produto
	 * 
	 * @param objDto
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarProduto(@RequestBody ProdutoDTO obj, @PathVariable Integer id) {
		
		Produto prd = produtoService.fromDTO(obj);	
		prd.setId(id);
		
		produtoService.alterarProduto(prd);
		
		return ResponseEntity.noContent().build();
	}

}
