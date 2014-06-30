/**
 * 
 */
package ar.com.bgr.serrano.controller.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Eoi;
import ar.com.bgr.serrano.service.ClienteService;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author Maxi
 * 
 * @since 31/05/2014
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
    /**
	 * Devuelve la cliente ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Eoi get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las clientes. 
	 */
	@RequestMapping(value="list",method = RequestMethod.GET)
	public @ResponseBody List<Eoi> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una cliente.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Eoi execute(@RequestBody Eoi cliente) {
		return service.save(cliente);
	}
	
	/**
	 * Da de baja la cliente ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
}
