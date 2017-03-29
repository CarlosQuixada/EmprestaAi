package br.ufc.npi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.npi.bean.Objeto;
import br.ufc.npi.bean.Usuario;
import br.ufc.npi.service.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value="/cadastrarUsuarioForm")
	public String cadastrarUsuarioTelaForm(Model model){
		Usuario usuario = new Usuario();
		model.addAttribute("usuario",usuario);
		return "usuario/cadastrarUsuarioForm";
	}
	
	@RequestMapping(value="/cadastrarUsuario")
	public String cadastrarUsuario(Usuario usuario){
		List<Objeto> objs = new ArrayList<>();
		usuario.setObjetosUsuario(objs);
		usuarioService.cadastrarUsuario(usuario);
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login(HttpSession session,@RequestParam String nome,@RequestParam String senha){
		Usuario usuario = usuarioService.buscarUsuarioNome(nome);
		if(usuario != null){
			if(usuario.getSenha().equals(senha)){
				session.setAttribute("usuario_logado",usuario);
				return "redirect:home";
			}
		}
		return"index";
	}
	
	@RequestMapping(value="/buscarAmigos")
	public String buscarAmigos(HttpSession session, Model model){
		List<Usuario> usuarios = usuarioService.listarUsuario();
		Usuario usuarioLogado = (Usuario)session.getAttribute("usuario_logado");
		model.addAttribute("usuario_logado",usuarioLogado);
		model.addAttribute("usuarios", usuarios);
		return "usuario/buscarAmigos";
	}
	
	@RequestMapping(value="/verObjetos/{idUsuario}", method=RequestMethod.GET)
	public String verObjetos(@PathVariable Integer idUsuario,Model model){
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		model.addAttribute("usuario", usuario);
		return "objeto/verObjeto";
	}
}
