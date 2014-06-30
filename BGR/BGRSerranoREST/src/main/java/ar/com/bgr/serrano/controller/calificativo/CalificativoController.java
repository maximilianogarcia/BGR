package ar.com.bgr.serrano.controller.calificativo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Calificativo;
import ar.com.bgr.serrano.service.CalificativoService;

@Controller
@RequestMapping("/calificativo")
public class CalificativoController {
	@Autowired
	CalificativoService service;

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Calificativo> list() {
		return service.list();
	}

	@RequestMapping(value="/listCliente", method = RequestMethod.GET)
	public @ResponseBody
	List<Calificativo> listCliente() {
		return service.listCliente();
	}
	
	@RequestMapping(value="/listProveedor", method = RequestMethod.GET)
	public @ResponseBody
	List<Calificativo> listProveedor() {
		return service.listProveedor();
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public @ResponseBody Calificativo create(@RequestBody Calificativo calificativo) {
		return service.create(calificativo);
	}

	@RequestMapping(value="/updateCliente", method = RequestMethod.POST)
	public @ResponseBody Calificativo updateCliente(@RequestBody Calificativo calificativo) {
		return service.updateCliente(calificativo);
	}
	
	@RequestMapping(value="/updateProveedor", method = RequestMethod.POST)
	public @ResponseBody Calificativo updateProveedor(@RequestBody Calificativo calificativo) {
		return service.updateProveedor(calificativo);
	}
}
