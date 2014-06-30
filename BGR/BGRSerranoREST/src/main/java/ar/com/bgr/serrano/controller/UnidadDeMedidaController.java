/**
 * 
 */
package ar.com.bgr.serrano.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.jackson.ProductoPaqueteImpresentation;
import ar.com.bgr.serrano.model.UnidadDeMedida;
import ar.com.bgr.serrano.service.UnidadDeMedidaService;
import ar.com.bgr.serrano.utils.PaqueteFromQuery;

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
	public @ResponseBody Set<UnidadDeMedida> getUndivisible() {
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
	
	@RequestMapping(value="getTotalByPaquetes", method = RequestMethod.POST)
	public @ResponseBody ProductoPaqueteImpresentation<List<PaqueteFromQuery>> getTotalByPaquetes(@RequestBody ProductoPaqueteImpresentation<List<PaqueteFromQuery>> mImpresentation) {

	    List<PaqueteFromQuery> paquetes = mImpresentation.getData();

	    List<PaqueteFromQuery> totales = new ArrayList<PaqueteFromQuery>();

		//$respuesta = Array("producto_id"=>$object->getProductoId());		
	    ProductoPaqueteImpresentation<List<PaqueteFromQuery>> respuesta = new ProductoPaqueteImpresentation<List<PaqueteFromQuery>>();
		respuesta.setId(mImpresentation.getId());
		
		for(PaqueteFromQuery p : paquetes){
			
			UnidadDeMedida unidad = service.getById(p.getUnidad());
			Double valor = p.getCantidad();
			
			Integer unidadReal = p.getUnidad();
			String unidad_nombre = unidad.getName();
			
	    	if (unidad.getDivisible()) {
	    		valor *=  unidad.getEquivalencia();
	    		unidadReal = unidad.getDerivaDe().getId();
	    		unidad_nombre = unidad.getDerivaDe().getName();
	    	}
	    	
//	    	totales.put("cantidad",valor.toString());
//	    	totales.put("unidad_id",unidadReal.toString());
//	    	totales.put("nombre",unidad_nombre);
//	    	totales.put("paquete_id",p.getId().toString());
	    	
	    	PaqueteFromQuery toTot = new PaqueteFromQuery();
	    	toTot.setCantidad(valor);
	    	toTot.setUnidad(unidadReal);
	    	toTot.setNombre(unidad_nombre);
	    	toTot.setId(p.getId());
	    	
	    	totales.add(toTot);
		}
			
		respuesta.setData(totales);
//	    $respuesta["totales"]=$totales;
//		$jsonContent = $serializer->serialize($respuesta, 'json');
//		
//		$response = new Response($jsonContent );
//		$response->headers->set('Content-Type', 'application/json');
//		return $response;
		
		return respuesta;
	}
	
	
}
