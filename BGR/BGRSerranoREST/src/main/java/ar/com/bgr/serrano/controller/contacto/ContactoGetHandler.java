package ar.com.bgr.serrano.controller.contacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.service.ContactoService;

@Controller
@RequestMapping("/contacto/get/{id}")
public class ContactoGetHandler {
	@Autowired
	ContactoService service;
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Contacto execute(@PathVariable("id") int id) {
		return service.getById(id);
	}
}
