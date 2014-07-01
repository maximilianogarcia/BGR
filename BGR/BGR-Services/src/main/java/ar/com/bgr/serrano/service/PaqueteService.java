package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.PaqueteDAO;
import ar.com.bgr.serrano.model.Paquete;
import ar.com.bgr.serrano.utils.PaqueteFromQuery;

@Service
@Transactional
public class PaqueteService {

	@Autowired
	PaqueteDAO dao;
	
	public Paquete save(Paquete paquete) {
		return dao.saveOrUpdate(paquete);
	}

	public List<Paquete> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Paquete getById(int id) {
		return dao.getById(id);
	}

	public List<PaqueteFromQuery> getPaquetesByProducto(Integer id) {		
		return dao.getPaquetesByProducto(id);
	}

	public void markMixed(int id) {
		dao.markMixed(id);	
	}

}
