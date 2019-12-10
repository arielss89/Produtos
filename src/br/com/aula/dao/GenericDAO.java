package br.com.aula.dao;

import java.util.List;

public interface GenericDAO<T> {
	void inserir(T objeto);
	void atualizar(T objeto);
	void remover(T objeto);
	List<T> listarTodos();
}
