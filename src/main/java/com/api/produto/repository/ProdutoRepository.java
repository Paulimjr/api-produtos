package com.api.produto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.produto.entity.Produto;

/**
 * Repositorio para controlar dados de produto
 * 
 * @author paulo
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
}
