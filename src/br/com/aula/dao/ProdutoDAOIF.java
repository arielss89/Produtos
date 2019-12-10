package br.com.aula.dao;

import br.com.aula.model.Produto;

public interface ProdutoDAOIF extends GenericDAO<Produto> {
	
	Produto buscarPorId(Long id);

}
