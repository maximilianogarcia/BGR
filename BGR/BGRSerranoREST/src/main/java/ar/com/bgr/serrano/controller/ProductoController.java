/**
 * 
 */
package ar.com.bgr.serrano.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.dao.exception.ConstraintViolatedException;
import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.model.ProductoProveedor;
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
@Controller
@RequestMapping("/producto")
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
	 * Da de alta o Actualiza en caso de existir una categoria.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="update", method = RequestMethod.POST)
	public @ResponseBody Producto executeUpdate(@RequestBody Producto producto) {
		return service.update(producto);
	}
	
	/**
	 * Da de baja la categoria ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
		try {
			service.removeRelations(id);
			service.remove(id);
		}catch(DataIntegrityViolationException e){
			throw new ConstraintViolatedException();
		}
       return true;
	}
	
	/**
	 * Lista todas las categorias. 
	 */
	@RequestMapping(value="getAllProductoRelations",method = RequestMethod.POST)
	public @ResponseBody Set<ProductoProveedor> getAllProductoRelations(@RequestBody Integer id) {
		return service.getById(id).getProductoProveedor();
	}
	
	/**
	 * Lista todas los productos que estan relacionados con la categoria. 
	 */
	@RequestMapping(value="getByCategoria",method = RequestMethod.POST)
	public @ResponseBody List<Producto> getProductsByCategoria(@RequestBody Integer categoriaId) {
		return service.getProductsByCategoria(categoriaId);
	}
	
	
	
}
