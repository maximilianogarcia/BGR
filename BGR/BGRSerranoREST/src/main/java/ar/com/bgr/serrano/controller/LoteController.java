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

import ar.com.bgr.serrano.model.Lote;
import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.service.LoteService;

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
@RequestMapping("/lote")
public class LoteController {
	
	@Autowired
	LoteService service;
	
    /**
	 * Devuelve la lote ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Lote get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las lotes. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<Lote> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una lote.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Lote execute(@RequestBody Lote lote) {
		return service.save(lote);
	}
	
	/**
	 * Da de baja la lote ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
	
	/**
	 * Lista todas las lotes de un producto. 
	 */
	@RequestMapping(value="getLotesByProducto",method = RequestMethod.GET)
	public @ResponseBody List<Lote> executeLotesByProducto(Producto producto) {
		return service.getLotesByProducto(producto);
	}
	
	/**
	 * Da de baja la lote ligada al identificador.
     */
	@RequestMapping(value="desactivar",method = RequestMethod.POST)
	public @ResponseBody Lote desactivar(@RequestBody Integer id) {
		return service.desactivar(id);
	}
}
