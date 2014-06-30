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

import ar.com.bgr.serrano.model.Mix;
import ar.com.bgr.serrano.service.CategoriaService;
import ar.com.bgr.serrano.service.MixService;
import ar.com.bgr.serrano.service.PaqueteService;
import ar.com.bgr.serrano.service.ProductoService;
import ar.com.bgr.serrano.service.RemanenteService;
import ar.com.bgr.serrano.service.UnidadDeMedidaService;
import ar.com.bgr.serrano.utils.MixImpresentation;

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
@RequestMapping("/mix")
public class MixController {
	
	@Autowired
	MixService service;
	@Autowired
	CategoriaService categoriaService;
	@Autowired
	UnidadDeMedidaService unidadDeMedidaService;	
	@Autowired
	ProductoService productoService;
	@Autowired
	RemanenteService remanenteService;
	@Autowired
	PaqueteService paqueteService;
	
    /**
	 * Devuelve el mix ligado al identificador recibido
     */
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public @ResponseBody Mix get(@PathVariable("id") int id) {
		return service.getById(id);
	}

	/**
	 * Lista todos los mix. 
	 */
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public @ResponseBody List<Mix> execute() {
		return service.list();
	}
	
	/**
	 * Da de alta o Actualiza en caso de existir un mix.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public @ResponseBody Mix execute(@RequestBody Mix mix) {
		return service.save(mix);
	}
	
	/**
	 * Da de baja el mix ligada al identificador.
     */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public @ResponseBody Boolean execute(@RequestBody Integer id) {
       service.remove(id);
       return true;
	}
	 
	/**
	 * Da de alta o Actualiza en caso de existir un mix.
	 * {"id":anIdValue,"name":"aName","descripcion":"aDescription"}
	 */
	@RequestMapping(value="saveMix", method = RequestMethod.POST)
	public @ResponseBody Mix executeSaveMix(@RequestBody MixImpresentation mixImpresentation) {
		return service.saveMix(mixImpresentation);
	}
}
