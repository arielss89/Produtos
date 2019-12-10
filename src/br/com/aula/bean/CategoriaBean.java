package br.com.aula.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.aula.dao.CategoriaDAO;
import br.com.aula.model.Categoria;

@Named
@javax.faces.view.ViewScoped
public class CategoriaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private Categoria categoria;
	@Inject
	private CategoriaDAO categoriaDao;

	private int categoriaId;
	private List<Categoria> categorias = new ArrayList<Categoria>();

	public CategoriaBean() {
		
	}
	
	@PostConstruct
	public void init() {
		this.categorias = categoriaDao.listarTodos();
		System.out.println(categorias);
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public CategoriaDAO getCategoriaDao() {
		return categoriaDao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void cadastrarCategoria() {
		categoriaDao.inserir(categoria);
		this.categoria = new Categoria();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

}
