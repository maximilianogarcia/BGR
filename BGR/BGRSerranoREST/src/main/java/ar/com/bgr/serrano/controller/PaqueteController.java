/**
 * 
 */
package ar.com.bgr.serrano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.jackson.ProductoPaqueteImpresentation;
import ar.com.bgr.serrano.model.Paquete;
import ar.com.bgr.serrano.service.PaqueteService;
import ar.com.bgr.serrano.utils.PaqueteFromQuery;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 31/05/2014
 */
@Controller
@RequestMapping("/paquete")
public class PaqueteController {
	
	@Autowired
	PaqueteService service;
	
    /**
	 * Devuelve el Paquete ligado al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Paquete get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todos los Paquete. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<Paquete> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir un Paquete.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Paquete execute(@RequestBody Paquete paquete) {
		return service.save(paquete);
	}
	
	/**
	 * Da de baja el Paquete ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
	
	/**
	 * Lista los paquetes disponibles que estan ligados a un producto
	 */
	@RequestMapping(value="getPaquetesDisponiblesPorProducto",method = RequestMethod.POST)
	public @ResponseBody ProductoPaqueteImpresentation<List<PaqueteFromQuery>> executeG(@RequestBody Integer id) {		
		return new ProductoPaqueteImpresentation<List<PaqueteFromQuery>>(service.getPaquetesByProducto(id),id);
	}
	 
}
