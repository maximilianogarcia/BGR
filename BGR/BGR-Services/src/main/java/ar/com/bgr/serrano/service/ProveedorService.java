package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.EOIDao;
import ar.com.bgr.serrano.model.Eoi;

@Component
@Transactional
public class ProveedorService {

	@Autowired
	EOIDao dao;

	public Eoi save(Eoi proveedor) {
		return dao.saveOrUpdateProveedor(proveedor);
	}

	public List<Eoi> list() {
		return dao.listProveedores();
	}

	public void remove(int id) {
		dao.removeProveedor(id);		
	}

	public Eoi getById(int id) {
		return dao.getProveedorById(id);
	}

}
