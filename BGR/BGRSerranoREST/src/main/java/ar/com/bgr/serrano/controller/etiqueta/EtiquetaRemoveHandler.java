package ar.com.bgr.serrano.controller.etiqueta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.service.EtiquetaService;


@Controller
@RequestMapping("/etiqueta/remove")
public class EtiquetaRemoveHandler{
	
	@Autowired
	EtiquetaService service;
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody
	Boolean execute( @RequestBody MultiValueMap<String,String> body			) {
		String id = body.getFirst("id");
        service.remove(Integer.parseInt(id));
       return true;
		
	}

}