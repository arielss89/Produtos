package br.com.aula.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.aula.dao.ProdutoDAO;
import br.com.aula.model.Categoria;
import br.com.aula.model.Produto;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Produto produto;
	@Inject
	private ProdutoDAO produtoDAO;
	@Inject
	private CategoriaBean categoriaBean;

	public ProdutoBean() {

	}

	public CategoriaBean getCategoriaBean() {
		return categoriaBean;
	}

	public void setCategoriaBean(CategoriaBean categoriaBean) {
		this.categoriaBean = categoriaBean;
	}

	public Produto getProduto() {
		return produto;
	}

	public void cadastrarCategoria() {
		if (categoriaBean.getCategoriaId() != 0) {
			Categoria categoria = categoriaBean.getCategoriaDao().buscarPorId(new Long(categoriaBean.getCategoriaId()));
			produto.adicionarCategoria(categoria);
			System.out.println(categoria.getNome());
		}
	}

	public void cadastrarProduto() {
		System.out.println(produto.getCategorias().size());
		if (produto.getCategorias().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("categorias",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione pelo menos uma Categoria",
							"O erro ocorreu porque uma categoria não foi selecionado e é obrigatorio selecionar uma categoria"));
			return;
		}

		if (produto.getId() != null) {
			produtoDAO.atualizar(produto);
			produto = new Produto();
			FacesContext.getCurrentInstance().addMessage("inputQuantidade", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atualizado com Sucesso!", "Produto Atualizado com Sucesso"));
			return;
		}

		produtoDAO.inserir(produto);
		produto = new Produto();

		FacesContext.getCurrentInstance().addMessage("inputQuantidade", new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Cadastrado com Sucesso!", "Produto Cadastrado com Sucesso"));
	}

	public void removerCategoria(Categoria categoria) {
		produto.getCategorias().remove(categoria);
	}

	public List<Produto> getProdutos() {
		return produtoDAO.listarTodos();
	}

	public void removerProduto(Produto produto) {
		produtoDAO.remover(produto);
	}

	public void alterarProduto(Produto produto) {
		this.produto = produtoDAO.buscarPorId(produto.getId());
	}

	public String redirecionarInserirCategoria() {
		return "cadastroCategoria?faces-redirect=true";
	}

	public void validarQuantidade(FacesContext facesContext, UIComponent uiComponent, String object)
			throws ValidatorException {
		String quantidade = (String) object;

		try {
			Integer.valueOf(quantidade);
		} catch (NumberFormatException e) {
			FacesContext.getCurrentInstance().addMessage("inputQuantidade", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Digite uma quantidade de zero a dez", "Error ao inserir uma quantidade"));
		}
	}

}
