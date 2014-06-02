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

	public void remove(int id) {
		dao.remove(id);		
	}

	public Contacto getById(int id) {
		return dao.getById(id);
	}

}
