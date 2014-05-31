package ar.com.bgr.serrano.controller.calificativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Calificativo;
import ar.com.bgr.serrano.service.CalificativoService;

@Controller
@RequestMapping(value="/calificativo/save")
public class CalificativoSaveOrUpdateHandler {
	@Autowired
	CalificativoService service;
	
@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Calificativo execute( @RequestBody MultiValueMap<String,String> body			) {
		Calificativo calificativo= new Calificativo();
		
		String name = body.getFirst("name");
		calificativo.setName(name);
		
		
       return service.save(calificativo);
		
	}
}
