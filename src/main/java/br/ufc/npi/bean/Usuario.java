package br.ufc.npi.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@Column(name="USU_ID",nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idUsuario;
	private String nome;
	private String email;
	private String dataNascimento;
	private String endereco;
	private String telefone;
	private String senha;
	@OneToMany(mappedBy="usuario",targetEntity=Objeto.class,fetch=FetchType.EAGER,cascade= CascadeType.REMOVE)
	private List<Objeto> objetosUsuario;
	
	@OneToMany(mappedBy = "usuarioEmp", targetEntity = Objeto.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Objeto> pedidoEmprestimo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public List<Objeto> getObjetosUsuario() {
		return objetosUsuario;
	}
	
	public void setObjetosUsuario(List<Objeto> objetosUsuario) {
		this.objetosUsuario = objetosUsuario;
	}
	
	public void addObjetosUsuario(Objeto objeto) {
		this.objetosUsuario.add(objeto);
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Objeto> getPedidoEmprestimo() {
		return pedidoEmprestimo;
	}
	public void setPedidoEmprestimo(List<Objeto> pedidoEmprestimo) {
		this.pedidoEmprestimo = pedidoEmprestimo;
	}
	
}
