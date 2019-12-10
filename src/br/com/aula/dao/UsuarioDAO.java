package br.com.aula.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.aula.model.Usuario;
import br.com.aula.util.JPAUtil;

public class UsuarioDAO {
	
	public Usuario getUsuarioLogin(Usuario usuario) {
		
		Usuario u = null;
		
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT u FROM Usuario u WHERE "
				+ "u.email=:email and u.senha=:senha";
		
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		
		try {
			u = query.getSingleResult();
			return u;
		}catch (Exception e) {
			System.out.println("Error no Login!" + e.getMessage());
		}
		
		return u;
	}

}
