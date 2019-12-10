package br.com.aula.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100)
	private String nome;
	private int quantidade;
	private float preco;

	@JoinTable(name = "tb_produto_categoria",
			joinColumns = {@JoinColumn(name="fk_id_produto", 
			referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name="fk_id_categoria", 
			referencedColumnName = "id")})
	@ManyToMany
	private List<Categoria> categorias = new ArrayList<Categoria>();

	public Produto() {

	}

	public Produto(String nome, int quantidade, float preco, List<Categoria> categorias) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
		this.categorias = categorias;
	}

	public Produto(String nome, int quantidade, float preco) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}
	
	
	public void adicionarCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

}
