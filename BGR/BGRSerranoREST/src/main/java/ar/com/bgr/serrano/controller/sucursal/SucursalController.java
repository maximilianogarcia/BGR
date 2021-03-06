/**
 * 
 */
package ar.com.bgr.serrano.controller.sucursal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.model.Sucursal;
import ar.com.bgr.serrano.service.SucursalService;

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
@RequestMapping("/sucursal")
public class SucursalController {
	
	@Autowired
	SucursalService service;
	
    /**
	 * Devuelve la sucursal ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Sucursal get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las sucursals. 
	 */
	@RequestMapping(value="list",method = RequestMethod.GET)
	public @ResponseBody List<Sucursal> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una sucursal.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Sucursal execute(@RequestBody Sucursal sucursal) {
		return service.save(sucursal);
	}

	/**
	 * Da de alta o Actualiza en caso de existir una sucursal.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="addContact/{sucursalId}", method = RequestMethod.POST)
	public @ResponseBody Contacto addExistingContact(@RequestBody Contacto contacto, @PathVariable("sucursalId") int sucursalId) {
         service.addExistingContact(contacto,sucursalId);
		return contacto;
	}
	
	/**
	 * Devuelve la contacto ligada al identificador recibido
	 */
	@RequestMapping(value="listByProveedor/{id}",method = RequestMethod.GET)
	public @ResponseBody  List<Sucursal> getbyProveedor(@PathVariable("id") int id) {
		return service.listByProveedor(id);
	}

	/**
	 * Devuelve la contacto ligada al identificador recibido
	 */
	@RequestMapping(value="listByCliente/{id}",method = RequestMethod.GET)
	public @ResponseBody  List<Sucursal> getbyCliente(@PathVariable("id") int id) {
		return service.listByCliente(id);
	}

	
	
	/**
	 * Da de baja la sucursal ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
}
