/**
 * 
 */
package ar.com.bgr.serrano.controller.contacto;

import java.util.List;
import java.util.Map;

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
	 * Devuelve la contacto ligada al id de la sucursal recibida 
	 */
	@RequestMapping(value="listBySucursal/{id}",method = RequestMethod.GET)
	public @ResponseBody  List<Contacto> getbySucursal(@PathVariable("id") int id) {
		return service.listBySucursal(id);
	}

	/**
	 * Devuelve la contacto ligada al EOI ( proveedor/cliente)  recibido
	 */
	@RequestMapping(value="listByEoi/{id}",method = RequestMethod.GET)
	public @ResponseBody  List<Contacto> getbyEoi(@PathVariable("id") int id) {
		return service.listByEoi(id);
	}

	/**
	 * Devuelve la contacto ligada al EOI ( proveedor/cliente)  recibido
	 */
	@RequestMapping(value="listOthersByEoi/{proveedor_id}/{sucursal_id}",method = RequestMethod.GET)
	public @ResponseBody  List<Contacto> getOthersbyEoi(@PathVariable("proveedor_id") int proveedorId, @PathVariable("sucursal_id") int sucursalId) {
		return service.listOthersByEoi(proveedorId, sucursalId);
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
	/**
	 * Desvincula un contacto de una sucursal.
	 */
	@RequestMapping(value="deleteFromSucursal",method = RequestMethod.DELETE)
	public @ResponseBody Boolean removeFromSucursal(@RequestBody Map<String, Integer> map) {
		service.removeFromSucursal(map.get("contacto"),map.get("sucursal"));
		return true;
	}
}
