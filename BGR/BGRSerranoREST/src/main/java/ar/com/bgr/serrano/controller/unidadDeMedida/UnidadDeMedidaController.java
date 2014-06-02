/**
 * 
 */
package ar.com.bgr.serrano.controller.unidadDeMedida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.UnidadDeMedida;
import ar.com.bgr.serrano.service.UnidadDeMedidaService;

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
@RequestMapping("/unidadDeMedida")
public class UnidadDeMedidaController {
	
	@Autowired
	UnidadDeMedidaService service;
	
    /**
	 * Devuelve la categoria ligada al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody UnidadDeMedida get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todas las categorias. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<UnidadDeMedida> execute() {
		return service.list();
	}
	
	/**
	 * Lista todas las categorias. 
	 */
	@RequestMapping(value="getNoDivisibles",method = RequestMethod.GET)
	public @ResponseBody List<UnidadDeMedida> getUndivisible() {
		return service.listNoDivisibles();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir una categoria.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody UnidadDeMedida execute(@RequestBody UnidadDeMedida unidad) {
		return service.save(unidad);
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
