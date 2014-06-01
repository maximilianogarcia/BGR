package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.EtiquetaDao;
import ar.com.bgr.serrano.model.Etiqueta;

@Component
@Transactional
public class EtiquetaService {

	@Autowired
	EtiquetaDao dao;
	
	public Etiqueta save(Etiqueta etiqueta) {
		return dao.saveOrUpdate(etiqueta);
	}

	public List<Etiqueta> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Etiqueta getById(int id) {
		return dao.getById(id);
	}
}
