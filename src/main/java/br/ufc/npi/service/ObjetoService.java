package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Objeto;
import br.ufc.npi.repository.ObjetoRepository;

@Service
public class ObjetoService {
	@Autowired
	ObjetoRepository objetoRepository;

	public void cadastrarObjeto(Objeto objeto) {
		this.objetoRepository.save(objeto);
	}

	public Objeto buscarObjeto(Integer id) {
		return this.objetoRepository.findOne(id);
	}

	public void deletarObjeto(Objeto objeto) {
		this.objetoRepository.delete(objeto);
	}

	public List<Objeto> listarObjeto() {
		return (List<Objeto>) this.objetoRepository.findAll();
	}
}
