package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.SucursalDao;
import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.model.Sucursal;

@Component
@Transactional
public class SucursalService {

	@Autowired
	SucursalDao dao;
	
	public Sucursal save(Sucursal sucursal) {
		return dao.saveOrUpdate(sucursal);
	}

	public List<Sucursal> list() {
		return dao.list();
	}
	
	public List<Sucursal> listByProveedor(int proveedor_id) {
		return dao.listByProveedor(proveedor_id);
	}

	public void remove(int id) {
		dao.remove(id);		
	}

	public Sucursal getById(int id) {
		return dao.getById(id);
	}

	public Sucursal addExistingContact(Contacto contacto, int sucursalId) {
		return dao.addExistingContact(contacto, sucursalId);
	}
}
