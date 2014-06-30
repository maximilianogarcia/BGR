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

import ar.com.bgr.serrano.model.ProductoProveedor;
import ar.com.bgr.serrano.model.Proveedor;
import ar.com.bgr.serrano.service.ProveedorService;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 01/06/2014
 */
@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	ProveedorService service;
	
	/**
	 * Devuelve la categoria ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Proveedor get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las categorias. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<Proveedor> execute() {
		return service.list();
	}
	
	/**
	 * Lista todas las categorias. 
	 */
	@RequestMapping(value="getProveedoresByProductoId",method = RequestMethod.POST)
	public @ResponseBody List<Proveedor> getProveedoresByProductoId(@RequestBody Integer id) {
		return service.list();
	}
	
	/**
	 * guarda la relacion entre un producto y un proveedor
	 */
	@RequestMapping(value="saveRelation",method = RequestMethod.POST)
	public @ResponseBody ProductoProveedor saveRelation(@RequestBody ProductoProveedor productoProveedor) {
		return service.saveRelation(productoProveedor);
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una categoria.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Proveedor execute(@RequestBody Proveedor proveedor) {
		return service.save(proveedor);
	}
	
	/**
	 * Da de baja la categoria ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
}
