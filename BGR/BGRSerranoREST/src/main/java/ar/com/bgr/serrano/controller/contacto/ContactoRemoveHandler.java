package ar.com.bgr.serrano.controller.contacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.service.ContactoService;


@Controller
@RequestMapping("/contacto/remove/{id}")
public class ContactoRemoveHandler{
	
	@Autowired
	ContactoService service;
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody
	Boolean execute( @PathVariable("id") int id) {
        service.remove(id);
       return true;
		
	}

}
