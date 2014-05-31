package ar.com.bgr.serrano.controller.etiqueta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Etiqueta;
import ar.com.bgr.serrano.service.EtiquetaService;

@Controller
@RequestMapping(value="/etiqueta/save")
public class EtiquetaSaveOrUpdateHandler {
	@Autowired
	EtiquetaService service;
	
@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Etiqueta execute( @RequestBody MultiValueMap<String,String> body			) {
		Etiqueta etiqueta = new Etiqueta();
		String name = body.getFirst("name");
		etiqueta.setName(name);
       return service.save(etiqueta);
		
	}
}
