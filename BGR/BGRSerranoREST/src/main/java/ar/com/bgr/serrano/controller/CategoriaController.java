/**
 * 
 */
package ar.com.bgr.serrano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.dao.exception.ConstraintViolatedException;
import ar.com.bgr.serrano.model.Categoria;
import ar.com.bgr.serrano.service.CategoriaService;

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
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService service;
	
    /**
	 * Devuelve la categoria ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Categoria get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las categorias. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<Categoria> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una categoria.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Categoria execute(@RequestBody Categoria categoria) {
		return service.save(categoria);
	}
	
	/**
	 * Da de baja la categoria ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
		try {
    	   service.remove(id);
		}catch(DataIntegrityViolationException e){
			throw new ConstraintViolatedException();
		}
       return true;
	}
}
