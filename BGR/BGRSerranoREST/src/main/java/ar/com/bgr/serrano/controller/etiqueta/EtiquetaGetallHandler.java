package ar.com.bgr.serrano.controller.etiqueta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Etiqueta;
import ar.com.bgr.serrano.service.EtiquetaService;

@Controller
@RequestMapping("/etiqueta/list")
public class EtiquetaGetallHandler {
	@Autowired
	EtiquetaService service;
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Etiqueta> getAll() {
		return service.list();
	}
}
