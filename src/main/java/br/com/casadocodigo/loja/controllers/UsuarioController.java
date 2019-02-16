package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private RoleDAO roleDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		List<Usuario> usuarios = usuarioDao.listar();
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	@RequestMapping(value = "/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("usuarios/form");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		String senhaRepetida = request.getParameter("senhaRepetida");

		if (!usuario.getSenha().equals(senhaRepetida))
			result.addError(new ObjectError("senhaRepetida", "O campo senha e senha repetida devem ser iguais"));

		try {
			usuarioDao.loadUserByUsername(usuario.getEmail());
			result.rejectValue("email", "field.registration_already_exists");
		} catch (UsernameNotFoundException e) {
			if (!result.hasErrors()) {
				usuario.setSenha(new BCryptPasswordEncoder().encode(senhaRepetida));
				usuarioDao.gravar(usuario);
				redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso");
			}
		}

		return result.hasErrors() ? form(usuario) : new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping(value="/permissoes", method=RequestMethod.GET)
	public ModelAndView permissoes(@RequestParam(name="email", required=true) String email) {
		ModelAndView modelAndView = new ModelAndView("usuarios/permissoes");
		
		modelAndView.addObject("permissoes", roleDao.listar());
		modelAndView.addObject("usuario", usuarioDao.loadUserByUsername(email));
				
		
		return modelAndView;
	}
	
	@RequestMapping(value="/permissoes", method=RequestMethod.POST)	
	public ModelAndView gravarPermissoes(Usuario usuario, RedirectAttributes redirectAttributes) {
		Usuario managed = usuarioDao.loadUserByUsername(usuario.getEmail());
		managed.setRoles(usuario.getRoles());
		
		usuarioDao.atualizar(managed);
		
		redirectAttributes.addFlashAttribute("Permissões atualizadas com sucesso");
		
		return new ModelAndView("redirect:/usuarios");
	}
}
