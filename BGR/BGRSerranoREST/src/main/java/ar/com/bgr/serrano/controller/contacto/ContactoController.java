/**
 * 
 */
package ar.com.bgr.serrano.controller.contacto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.service.ContactoService;

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
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired
	ContactoService service;
	
    /**
	 * Devuelve la contacto ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Contacto get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las contactos. 
	 */
	@RequestMapping(value="list",method = RequestMethod.GET)
	public @ResponseBody List<Contacto> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una contacto.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Contacto execute(@RequestBody Contacto contacto) {
		return service.save(contacto);
	}
	
	/**
	 * Da de baja la contacto ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
}
