/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.ProductoDAO;
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
@Service
@Transactional
public class ProductoService {
	
	@Autowired
	ProductoDAO dao;
	
	public Producto save(Producto producto) {
		return dao.saveOrUpdate(producto);
	}
	
	public Producto update(Producto producto) {
		return dao.saveOrUpdateAndDelete(producto);
	}
	
	public List<Producto> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);
	}
	

	public void removeRelations(int id){
		dao.removeAllRelations(id);
	}
	
	
	public Producto getById(int id) {
		return dao.getById(id);
	}

	public List<Producto> getProductsByCategoria(Integer categoriaId){
		return dao.getProductosByCategoria(categoriaId);		
	}
	
}
