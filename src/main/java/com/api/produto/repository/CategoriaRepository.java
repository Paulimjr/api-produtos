package com.api.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.produto.entity.Categoria;

/**
 * Repositorio para controlar dados de categoria
 * 
 * @author paulo
 *
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
		
}
