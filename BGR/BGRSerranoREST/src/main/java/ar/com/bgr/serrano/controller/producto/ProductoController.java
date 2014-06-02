/**
 * 
 */
package ar.com.bgr.serrano.controller.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.service.ProductoService;

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
public class ProductoController {

	@Autowired
	ProductoService service;
	
	/**
	 * Devuelve la categoria ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Producto get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las categorias. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<Producto> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una categoria.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Producto execute(@RequestBody Producto producto) {
		return service.save(producto);
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
