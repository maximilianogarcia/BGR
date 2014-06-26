package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.ContactoDao;
import ar.com.bgr.serrano.model.Contacto;

@Component
@Transactional
public class ContactoService {

	@Autowired
	ContactoDao dao;
	
	public Contacto save(Contacto contacto) {
		return dao.saveOrUpdate(contacto);
	}

	public List<Contacto> list() {
		return dao.list();
	}

	public List<Contacto> listBySucursal(int id) {
		return dao.listBySucursal(id);
	}
	
	public List<Contacto> listByEoi(int id) {
		return dao.listByEoi(id);
	}
	
	public List<Contacto> listOthersByEoi(int proveedorId, int sucursalId) {
		return dao.listOthersByEoi(proveedorId,sucursalId );
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	public void removeFromSucursal(int ContactoId, int sucursalId) {
		dao.removeFromSucursal(ContactoId,sucursalId);		
	}

	public Contacto getById(int id) {
		return dao.getById(id);
	}

}
