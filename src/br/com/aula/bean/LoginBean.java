package br.com.aula.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.aula.dao.UsuarioDAO;
import br.com.aula.model.Usuario;

@ManagedBean
public class LoginBean {

	
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuarLogin() {
		
		if(usuarioDAO.getUsuarioLogin(usuario) != null) {
			FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().put("usuario", usuario);
			return "pages/cadastroProduto?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("erroLogin", 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha Inválidos", "erro ao tentar logar"));
		
		return null;
	}
	
}
