/**
 * 
 */
package ar.com.bgr.serrano.controller.Proveedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Eoi;
import ar.com.bgr.serrano.service.ProveedorService;

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
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	ProveedorService service;
	
    /**
	 * Devuelve la proveedor ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Eoi get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las proveedors. 
	 */
	@RequestMapping(value="list",method = RequestMethod.GET)
	public @ResponseBody List<Eoi> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una proveedor.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Eoi execute(@RequestBody Eoi proveedor) {
		return service.save(proveedor);
	}
	
	/**
	 * Da de baja la proveedor ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
}
