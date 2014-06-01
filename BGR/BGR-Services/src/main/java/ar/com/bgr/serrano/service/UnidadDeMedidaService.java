/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.UnidadDeMedidaDao;
import ar.com.bgr.serrano.model.UnidadDeMedida;

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
@Component
@Transactional
public class UnidadDeMedidaService {

	
	@Autowired
	UnidadDeMedidaDao dao;
	
	public UnidadDeMedida save(UnidadDeMedida unidad) {
		return dao.saveOrUpdate(unidad);
	}

	public List<UnidadDeMedida> list() {
		return dao.list();
	}
	
	public List<UnidadDeMedida> listNoDivisibles() {
		return dao.listNoDivisibles();
	}	

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public UnidadDeMedida getById(int id) {
		return dao.getById(id);
	}
}
