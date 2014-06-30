/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.EOIDAO;
import ar.com.bgr.serrano.dao.ProductoProveedorDAO;
import ar.com.bgr.serrano.dao.ProveedorDAO;
import ar.com.bgr.serrano.model.Eoi;
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
	EOIDAO daoE;

	public Eoi save(Eoi proveedor) {
		return daoE.saveOrUpdateProveedor(proveedor);
	}

	public List<Eoi> list() {
		return daoE.listProveedores();
	}

	public void remove(int id) {
		daoE.removeProveedor(id);		
	}

	public Eoi getById(int id) {
		return daoE.getProveedorById(id);
	}
	
	@Autowired
	ProductoProveedorDAO rdao;
	
	public Proveedor save(Proveedor proveedor) {
		return dao.saveOrUpdate(proveedor);
	}

	public List<Proveedor> listP() {
		return dao.list();
	}

	public void removeP(int id) {
		dao.remove(id);		
	}
	
	public Proveedor getByIdP(int id) {
		return dao.getById(id);
	}
	
	public ProductoProveedor saveRelation(ProductoProveedor productoProveedor){
		return rdao.saveOrUpdate(productoProveedor);
	}
	
	public List<ProductoProveedor> listRelations(int id) {
		return null; //rdao.findByProductoId(id);
	}
}
