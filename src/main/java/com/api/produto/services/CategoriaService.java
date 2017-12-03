package com.api.produto.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.produto.dto.CategoriaDTO;
import com.api.produto.entity.Categoria;
import com.api.produto.entity.CategoriaProduto;
import com.api.produto.entity.Produto;
import com.api.produto.repository.CategoriaRepository;
import com.api.produto.services.exceptions.ObjectNotFoundException;
/**
 * Controlar serviços relacionado a categoria
 * 
 * @author paulo
 *
 */
@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	/**
	 * Método para buscar uma categoria pelo ID
	 * 
	 * @author paulo
	 * @throws ObjectNotFoundException
	 * @param id o identificador da categoria
	 * @return a categoria caso encontre. 
	 */
	public Categoria buscar(final Integer id) {
		Categoria cat = categoriaRepository.findOne(id);
		
		if (cat == null) {
			throw new ObjectNotFoundException(String.format("Categoria com o ID %s não foi encontrada.", id));
		}
		
		return cat;
	}
	
	/**
	 * Retorna todas as categorias cadastradas
	 * 
	 * @return
	 */
	public List<Categoria> todasCategorias() {
		return this.categoriaRepository.findAll();
	}
	
	/**
	 * Método para inserir uma nova categoria
	 * 
	 * @param obj
	 * @return
	 */
	public Categoria insert(final Categoria cat) {
		 cat.setId(null);
		 return categoriaRepository.save(cat);
	}
	
	/**
	 * Convertendo CategoriaDTO para Categoria
	 * 
	 * @param obj
	 * @return
	 */
	public Categoria fromDTO(CategoriaDTO obj) {
		System.out.println("categoia inserindo: "+obj.toString());
		return new Categoria(obj.getId(), obj.getNome());
	}
	
	/**
	 * Método para inserir categoria a um novo produto
	 * 
	 * @param catPrd
	 * @return
	 */
	public Categoria inserirCategoriaProduto(CategoriaProduto catPrd) {
		
		Categoria cat = this.buscar(catPrd.getCategoriaId());
		Produto produto = this.produtoService.buscarProduto(catPrd.getProdutoId());
		
		List<Categoria> list = this.todasCategorias();
		
		if (list != null) {
			for (Categoria c : list) {
				if (c.getProdutos() != null) {
					for (Produto p : c.getProdutos()) {
						if (p.getId().equals(produto.getId())) {
							throw new ObjectNotFoundException("O produto informado já tem um categoria cadastrada!");
						}
					}
				}
			}
		}
		
		produto.setCategorias(Arrays.asList(cat));
		produtoService.inserirCategoria(produto);
		
		return cat;
	}
		
}
