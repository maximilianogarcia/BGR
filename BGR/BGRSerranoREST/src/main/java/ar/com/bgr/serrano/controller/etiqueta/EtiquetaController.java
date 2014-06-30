package ar.com.bgr.serrano.controller.etiqueta;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Etiqueta;
import ar.com.bgr.serrano.service.EtiquetaService;

@Controller
@RequestMapping("/etiqueta")
public class EtiquetaController {
	@Autowired
	EtiquetaService service;
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Etiqueta> getAll() {
		return service.list();
	}
	
	/**
	 * Desvincula un tag de un proveedor.
	 */
	@RequestMapping(value="untag",method = RequestMethod.DELETE)
	public @ResponseBody Etiqueta unTag(@RequestBody Map<String, Integer> map) {
		return service.unTag(map.get("etiqueta"),map.get("proveedor"));
	}
	
	
	
	/**
	 * crea una etiqueta
	 */
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Etiqueta create(@RequestBody String name) {
		return service.create(name);
	}
}
