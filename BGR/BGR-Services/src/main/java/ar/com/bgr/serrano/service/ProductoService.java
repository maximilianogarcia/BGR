/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.ProductoDao;
import ar.com.bgr.serrano.model.Producto;

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
public class ProductoService {
	
	@Autowired
	ProductoDao dao;
	
	public Producto save(Producto producto) {
		return dao.saveOrUpdate(producto);
	}

	public List<Producto> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Producto getById(int id) {
		return dao.getById(id);
	}

}
