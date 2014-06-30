package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.EtiquetaDAO;
import ar.com.bgr.serrano.model.Etiqueta;

@Service
@Transactional
public class EtiquetaService {

	@Autowired
	EtiquetaDAO dao;
	
	public Etiqueta save(Etiqueta etiqueta) {
		return dao.saveOrUpdate(etiqueta);
	}

	public Etiqueta create(String name) {
		Etiqueta etiqueta = new Etiqueta();
		etiqueta.setName(name);
		return dao.saveOrUpdate(etiqueta);
	}

	public List<Etiqueta> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	public Etiqueta unTag(int etiquetaId, int eoiId) {
		return dao.unTag(etiquetaId,eoiId);		
	}
	
	public Etiqueta getById(int id) {
		return dao.getById(id);
	}
}
