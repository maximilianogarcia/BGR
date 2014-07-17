/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.LoteDAO;
import ar.com.bgr.serrano.model.Lote;

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
public class LoteService {
	
	@Autowired
	LoteDAO dao;
	
	public Lote save(Lote lote) {
		return dao.saveOrUpdate(lote);
	}

	public List<Lote> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Lote getById(int id) {
		return dao.getById(id);
	}
	
	public List<Lote> getLotesByProducto(Integer productoId){
		return dao.getLotesByProducto(productoId);
	}
	
	public Lote desactivar(Integer id){
		return dao.desactivar(id);
	}
}
