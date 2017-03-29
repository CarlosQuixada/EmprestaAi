package br.ufc.npi.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Objeto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String descricao;
	private String nome;
	private boolean emprestado = false;
	@ManyToOne(optional=false)
	@JoinColumn(name="USU_ID",referencedColumnName="USU_ID")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="usuarioEmp_id")
	private Usuario usuarioEmp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEmprestado() {
		return emprestado;
	}

	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuarioEmp() {
		return usuarioEmp;
	}

	public void setUsuarioEmp(Usuario usuarioEmp) {
		this.usuarioEmp = usuarioEmp;
	}
}
