package com.api.produto.entity;

import java.io.Serializable;

public class CategoriaProduto implements Serializable {
	
	private static final long serialVersionUID = -5143557823974563287L;
	
	private Integer categoriaId;
	private Integer produtoId;
	/**
	 * @return the categoriaId
	 */
	public Integer getCategoriaId() {
		return categoriaId;
	}
	/**
	 * @param categoriaId the categoriaId to set
	 */
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	/**
	 * @return the produtoId
	 */
	public Integer getProdutoId() {
		return produtoId;
	}
	/**
	 * @param produtoId the produtoId to set
	 */
	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

}
