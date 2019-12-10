package br.com.aula.dao;

import br.com.aula.model.Categoria;

public interface CategoriaDAOIF extends GenericDAO<Categoria>{
	Categoria buscarPorId(Long id);
}
