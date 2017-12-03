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

import com.api.produto.dto.CategoriaDTO;
import com.api.produto.entity.Categoria;
import com.api.produto.entity.CategoriaProduto;
import com.api.produto.services.CategoriaService;

/**
 * Controlar as requisições de cateogorias
 * 
 * @author paulo
 *
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	/**
	 * API para consultar uma categoria pelo identificador
	 * 
	 * @author paulo
	 * @param id o identificador da categoria
	 * @return a categoria
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarCategoriaPeloId(@Valid @PathVariable final Integer id) {
		Categoria cat = this.categoriaService.buscar(id);
		return  ResponseEntity.ok().body(cat);
	}
	
	/**
	 * API para inserir uma categoria
	 * 
	 * @param objDto
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = categoriaService.fromDTO(objDto);
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * API para inserir uma categoria a um produto
	 * 
	 * @param objDto
	 * @return
	 */
	@RequestMapping(value="/inserirCateoriaProduto",method=RequestMethod.POST)
	public ResponseEntity<Void> insertCategoriaProduto(@Valid @RequestBody CategoriaProduto catPrd) {
		
		Categoria obj = categoriaService.inserirCategoriaProduto(catPrd);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
}
