package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.CalificativoDAO;
import ar.com.bgr.serrano.model.Calificativo;

@Service
@Transactional
public class CalificativoService {

	@Autowired
	CalificativoDAO dao;
	
	public Calificativo save(Calificativo calificativo) {
		return dao.saveOrUpdate(calificativo);
	}

	public List<Calificativo> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}

}
