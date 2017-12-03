package com.api.produto.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.produto.dto.ProdutoDTO;
import com.api.produto.entity.Categoria;
import com.api.produto.entity.Produto;
import com.api.produto.repository.CategoriaRepository;
import com.api.produto.repository.ProdutoRepository;
import com.api.produto.services.exceptions.ObjectNotFoundException;

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
		
	@Autowired
	private CategoriaService categoriaService;
	
	/**
	 * Método para inserir um novo produto
	 * 
	 * @param obj
	 * @return
	 */
	public Produto novoProduto(final Produto prd) {
		prd.setId(null);
		return produtoRepository.save(prd);
	}
	
	/**
	 * Método para alterar um produto cadastrado
	 * 
	 * @param obj
	 * @param id
	 * @return
	 */
	public Produto alterarProduto(final Produto obj) {
		Produto prd = produtoRepository.findOne(obj.getId());
		
		if (prd == null) {
			throw new ObjectNotFoundException("Produto não foi encontrado para alterar.");
		}
		
		prd.setNome(obj.getNome());
		prd.setPreco(obj.getPreco());
		
		produtoRepository.save(prd);
		
		return prd;
	}
	
	/**
	 * Inserindo uma categoria no produto
	 * 
	 * @param prd
	 * @return
	 */
	public Produto inserirCategoria (final Produto prd) {
		return produtoRepository.save(prd);
	}
	
	/**
	 * Convertendo ProtdutoDTO para Produto
	 * 
	 * @param obj
	 * @return
	 */
	public Produto fromDTO(final ProdutoDTO obj) {
		Produto prd = new Produto(null, obj.getNome(), obj.getPreco());
		
		Categoria categoria = new Categoria();
		categoria.setId(obj.getCategoria().getId());

		if (categoria.getId() != null) {
			categoria = categoriaService.buscar(categoria.getId());
			
			if (categoria == null) {
				throw new ObjectNotFoundException("Categoria com identificador : "+obj.getCategoria().getId()+"não foi encontrada!");
			}
			
			prd.setCategorias(Arrays.asList(categoria));
		}
		
		return prd;
	}
	
	/**
	 * Converter para alterar
	 * 
	 * @param obj
	 * @return
	 */
	public Produto fromDTOAlterar(final ProdutoDTO obj) {
		return new Produto(null,obj.getNome(), obj.getPreco());
	}

	/**
	 * Metodo para buscar um produto pelo Id
	 * 
	 * @param id o identificador
	 * @return
	 */
	public Produto buscarProduto(Integer id) {
		Produto prd = produtoRepository.findOne(id);
		
		if (prd == null) {
			throw new ObjectNotFoundException("Produto com identificador : "+id+" não foi encontrado!");
		}
		
		return prd;
	}

}
