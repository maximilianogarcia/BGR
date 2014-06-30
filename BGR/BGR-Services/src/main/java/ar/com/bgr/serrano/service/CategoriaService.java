package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.CategoriaDAO;
import ar.com.bgr.serrano.model.Categoria;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	CategoriaDAO dao;
	
	public Categoria save(Categoria categoria) {
		return dao.saveOrUpdate(categoria);
	}

	public List<Categoria> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Categoria getById(int id) {
		return dao.getById(id);
	}
}
