/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.ProductoProveedorDAO;
import ar.com.bgr.serrano.dao.ProveedorDAO;
import ar.com.bgr.serrano.model.ProductoProveedor;
import ar.com.bgr.serrano.model.Proveedor;

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
public class ProveedorService {
	
	@Autowired
	ProveedorDAO dao;
	
	@Autowired
	ProductoProveedorDAO rdao;
	
	public Proveedor save(Proveedor proveedor) {
		return dao.saveOrUpdate(proveedor);
	}

	public List<Proveedor> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Proveedor getById(int id) {
		return dao.getById(id);
	}
	
	public ProductoProveedor saveRelation(ProductoProveedor productoProveedor){
		return rdao.saveOrUpdate(productoProveedor);
	}
	
	public List<ProductoProveedor> listRelations(int id) {
		return null; //rdao.findByProductoId(id);
	}
}
