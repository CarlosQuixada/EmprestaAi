package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Usuario;
import br.ufc.npi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	public void cadastrarUsuario(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}

	public Usuario buscarUsuarioNome(String nome) {
		return this.usuarioRepository.findUsuarioByNome(nome);
	}

	public Usuario buscarUsuario(Integer id) {
		return this.usuarioRepository.findOne(id);
	}

	public void deletarUsuario(Usuario usuario) {
		this.usuarioRepository.delete(usuario);
	}

	public List<Usuario> listarUsuario() {
		return (List<Usuario>) this.usuarioRepository.findAll();
	}
}
