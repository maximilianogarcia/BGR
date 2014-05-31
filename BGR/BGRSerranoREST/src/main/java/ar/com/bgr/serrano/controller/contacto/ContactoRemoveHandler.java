package ar.com.bgr.serrano.controller.contacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.service.ContactoService;


@Controller
@RequestMapping("/contacto/remove")
public class ContactoRemoveHandler{
	
	@Autowired
	ContactoService service;
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody
	Boolean execute( @RequestBody MultiValueMap<String,String> body			) {
		String id = body.getFirst("id");
        service.remove(Integer.parseInt(id));
       return true;
		
	}

}
