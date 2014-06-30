package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.RemanenteDAO;
import ar.com.bgr.serrano.model.Remanente;

@Service
@Transactional
public class RemanenteService {

	@Autowired
	RemanenteDAO dao;
	
	public Remanente save(Remanente remanente) {
		return dao.saveOrUpdate(remanente);
	}

	public List<Remanente> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Remanente getById(int id) {
		return dao.getById(id);
	}
	
	public Remanente getByProducto(int id) {
		return dao.getByProducto(id);
	}
}
