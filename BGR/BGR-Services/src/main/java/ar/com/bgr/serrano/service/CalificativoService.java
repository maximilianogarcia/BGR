package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.CalificativoDao;
import ar.com.bgr.serrano.model.Calificativo;

@Component
@Transactional
public class CalificativoService {

	@Autowired
	CalificativoDao dao;
	
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
