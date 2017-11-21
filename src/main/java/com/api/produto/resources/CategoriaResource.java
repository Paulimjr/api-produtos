package com.api.produto.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.produto.entity.Categoria;
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
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarCategoriaPeloId(@PathVariable final Integer id) {
		Categoria cat = this.categoriaService.buscar(id);
		return  ResponseEntity.ok().body(cat);
	}
}
