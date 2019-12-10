package br.com.aula.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.aula.model.Categoria;
import br.com.aula.util.JPAUtil;

public class CategoriaDAO implements CategoriaDAOIF, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void inserir(Categoria objeto) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(objeto);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void atualizar(Categoria objeto) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.merge(objeto);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void remover(Categoria objeto) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria = em.find(Categoria.class, objeto.getId());
		em.remove(categoria);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public List<Categoria> listarTodos() {
		List<Categoria> categorias = null;
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT a FROM Categoria a";
		TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
		
		categorias = query.getResultList();
		
		em.close();
		
		return categorias;
	}

	@Override
	public Categoria buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		Categoria categoria = em.find(Categoria.class, id);
		
		em.close();
		return categoria;
	}

}
