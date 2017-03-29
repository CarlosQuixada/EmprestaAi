package br.ufc.npi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.npi.bean.Objeto;
import br.ufc.npi.bean.Usuario;
import br.ufc.npi.service.ObjetoService;
import br.ufc.npi.service.UsuarioService;
import br.ufc.quixada.npi.model.Email;
import br.ufc.quixada.npi.model.Email.EmailBuilder;
import br.ufc.quixada.npi.service.SendEmailService;

@Controller
public class ObjetoController {
	@Autowired
	ObjetoService objetoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	SendEmailService service;

	@RequestMapping(value="/cadastrarObjetoForm")
	public String cadastrarObjetoForm(){
		return "objeto/cadastrarObjetoForm";
	}
	

	@RequestMapping(value="/cadastrarObjeto")
	public String cadastrarObjeto(HttpSession session,Objeto objeto){
		Usuario usu = (Usuario) session.getAttribute("usuario_logado");
		objeto.setUsuario(usu);
		objetoService.cadastrarObjeto(objeto);
		return "home";
	}
	
	@RequestMapping(value="/pedirEmprestado/{idObjeto}")
	public String pedirEmprestado(@PathVariable Integer idObjeto,HttpSession session){
		Usuario usuario = (Usuario)session.getAttribute("usuario_logado");
		Objeto obj = objetoService.buscarObjeto(idObjeto);

		obj.setUsuarioEmp(usuario);
		obj.setEmprestado(true);
		
		//EmailBuilder emailBuilder = new EmailBuilder(usuario.getNome()+"- Empresta Aí",usuario.getEmail(),"Teste",obj.getUsuario().getEmail(), "Message Body");
		EmailBuilder emailBuilder = new EmailBuilder("CHUPA KURIRAS",usuario.getEmail(),"APRENDE A ENVIAR EMAIL BIXO FULERAGEM SEU BAIOTOLA","robson_cn1995@hotmail.com", "DEPOIS TE ENSINO A MANDAR EMAIL È BEM FACIM");
		
		Email email = new Email(emailBuilder);
		

		service.sendEmail(email);
		
		objetoService.cadastrarObjeto(obj);
		session.setAttribute("usuario_logado", usuario);	
		return "home";
	}
	
	@RequestMapping(value="/verPedidoEmprestimo")
	public String verPedidoEmprestimo(Model model,HttpSession session){
		Usuario usuarioLogado = (Usuario)session.getAttribute("usuario_logado");
		Usuario usu = usuarioService.buscarUsuario(usuarioLogado.getIdUsuario());
		List<Objeto> objsEmp = usu.getPedidoEmprestimo();
		model.addAttribute("listaObjetos", objsEmp);
		return "objeto/verPedidoEmprestimo";
	}
	
	@RequestMapping(value="/devolverObjetos/{idObjeto}",method=RequestMethod.GET)
	public String devolverObjetos(@PathVariable Integer idObjeto,HttpSession session){
		Objeto objeto = objetoService.buscarObjeto(idObjeto);
		objeto.setEmprestado(false);
		objeto.setUsuarioEmp(null);
		
		Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
		
		objetoService.cadastrarObjeto(objeto);
		session.setAttribute("usuario_logado", usuario);
		
		return "home";
	}
}
