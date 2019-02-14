package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedido;

@Controller
public class PedidosServicoController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "pedidos")
	public ModelAndView listar() {

		ResponseEntity<List<Pedido>> entity = restTemplate.exchange("https://book-payment.herokuapp.com/orders",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Pedido>>() {
				});
		ModelAndView modelAndView = new ModelAndView("pedidos");
		modelAndView.addObject("pedidos", entity.getBody());
		return modelAndView;
	}
}
