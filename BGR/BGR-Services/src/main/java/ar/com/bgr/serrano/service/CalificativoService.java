package ar.com.bgr.serrano.service;

import java.util.List;

import org.hibernate.ejb.event.ListenerCallback;
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
	
	public Calificativo create(Calificativo calificativo) {
		return dao.saveOrUpdate(calificativo);
	}
	public Calificativo updateProveedor(Calificativo calificativo) {
		return dao.updateProveedor(calificativo);
	}

	public Calificativo updateCliente(Calificativo calificativo) {
		return dao.updateCliente(calificativo);
	}

	public List<Calificativo> list() {
		return dao.list();
	}
	public List<Calificativo> listCliente() {
		return dao.listCliente();
	}
	public List<Calificativo> listProveedor() {
		return dao.listProveedor();
	}

	public void remove(int id) {
		dao.remove(id);		
	}

}
