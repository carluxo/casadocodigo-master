package br.com.casadocodigo.loja.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class RelatorioProdutosController {
	@Autowired
	private ProdutoDAO dao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
	}

	@RequestMapping(value = "relatorio-produtos", method = RequestMethod.GET)
	@ResponseBody
	public List<Produto> relatorio(
			@RequestParam(name = "data", defaultValue = "1900-01-01") @DateTimeFormat Date dataLancamento) {
		Calendar data = Calendar.getInstance();
		data.setTime(dataLancamento);
		return dao.relatorio(data);
	}

}
