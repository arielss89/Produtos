package br.com.aula.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import br.com.aula.model.Produto;
import br.com.aula.util.JPAUtil;

public class ProdutoDAO implements ProdutoDAOIF, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	@Override
	public void inserir(Produto objeto) {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(objeto);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void atualizar(Produto objeto) {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.merge(objeto);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void remover(Produto objeto) {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		Produto produto = em.find(Produto.class, objeto.getId());
		em.remove(produto);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public List<Produto> listarTodos() {
		List<Produto> produtos = null;
		em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT f FROM Produto f";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		produtos = query.getResultList();
		
		em.close();
		
		return produtos;
	}

	@Override
	public Produto buscarPorId(Long id) {
		em = JPAUtil.getEntityManager();
		
		Produto produto = em.find(Produto.class, id);
		
		Hibernate.initialize(produto.getCategorias());
		
		em.close();
		return produto;
	}

}
