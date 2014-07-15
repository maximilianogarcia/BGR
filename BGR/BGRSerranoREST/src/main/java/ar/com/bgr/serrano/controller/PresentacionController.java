/**
 * 
 */
package ar.com.bgr.serrano.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Presentacion;
import ar.com.bgr.serrano.service.PresentacionService;

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
@RequestMapping("/presentacion")
public class PresentacionController {
	
	@Autowired
	PresentacionService service;
	
    /**
	 * Devuelve la presentacion ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Presentacion get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las presentacions. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<Presentacion> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una presentacion.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Presentacion execute(@RequestBody Presentacion presentacion) {
		return service.save(presentacion);
	}
	
	/**
	 * Da de baja la presentacion ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
	
	/**
	 * Lista todas las presentaciones activadas. 
	 */
	@RequestMapping(value="getActives",method = RequestMethod.GET)
	public @ResponseBody List<Presentacion> executeGetActives() {
		return service.getActives();
	}
	
	/**
	 * Lista todas las presentacions desactivadas.
	 */
	@RequestMapping(value="getInactives",method = RequestMethod.GET)
	public @ResponseBody List<Presentacion> executeGetInactives() {
		return service.getInactives();
	}
	
	/**
	 * desactiva la presentacion ligada al identificador.
     */
	@RequestMapping(value="desactivar",method = RequestMethod.POST)
	public @ResponseBody Presentacion executeDesactivar(@RequestBody HashMap<String,String> map ) {
       return service.desactivar(Integer.parseInt(map.get("data")),map.get("message"));
	}
	
	/**
	 * activa la presentacion ligada al identificador.
     */
	@RequestMapping(value="activar",method = RequestMethod.POST)
	public @ResponseBody Presentacion executeActivar(@RequestBody HashMap<String,String> map ) {
       return service.activar(Integer.parseInt(map.get("data")),map.get("message"));
	}
	
	@RequestMapping(value="getStocks",method = RequestMethod.GET)
	public @ResponseBody List<Object[]> getStocks() {
       return service.getStocks();
	}
	
	@RequestMapping(value="getStocksByCategoria/{categoriaID}",method = RequestMethod.GET)
	public @ResponseBody List<Object[]> getStocksByCategory(@PathVariable("categoriaID") Integer id ) {
       return service.getStocksByCategory(id);
	}
	
	@RequestMapping(value="getStocksByProducto",method = RequestMethod.POST)
	public @ResponseBody List<Object[]> getStocksByProduct(@RequestBody Integer id ) {
       return service.getStocksByProduct(id);
	}
	

}
