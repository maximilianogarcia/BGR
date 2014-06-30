/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.UnidadDeMedidaDAO;
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
@Service
@Transactional
public class UnidadDeMedidaService {

	
	@Autowired
	UnidadDeMedidaDAO dao;
	
	public UnidadDeMedida save(UnidadDeMedida unidad) {
		return dao.saveOrUpdate(unidad);
	}

	public List<UnidadDeMedida> list() {
		return dao.list();
	}
	
	public Set<UnidadDeMedida> listNoDivisibles() {
		return dao.listNoDivisibles();
	}	

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public UnidadDeMedida getById(int id) {
		return dao.getById(id);
	}
}
