package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.PresentacionDAO;
import ar.com.bgr.serrano.model.Presentacion;

@Service
@Transactional
public class PresentacionService {

	@Autowired
	PresentacionDAO dao;
	
	public Presentacion save(Presentacion presentacion) {
		return dao.saveOrUpdate(presentacion);
	}

	public List<Presentacion> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Presentacion getById(int id) {
		return dao.getById(id);
	}
	
	public List<Presentacion> getInactives(){
		return dao.getPresentacionesByState(Boolean.FALSE);
	}
	
	public List<Presentacion> getActives(){
		return dao.getPresentacionesByState(Boolean.TRUE);
	}

	public Presentacion desactivar(Integer id, String message) {
		return dao.changeState(id,message,Boolean.TRUE);
	}
	
	public Presentacion activar(Integer id, String message) {
		return dao.changeState(id,message,Boolean.FALSE);
	}	
	
	public List<Object[]> getStocks(){
		return dao.getStocks();
	}
	
	public List<Object[]> getStocksByCategory(Integer id){
		return dao.getStocksByCategory(id);		
	}
	
	public List<Object[]> getStocksByProduct(Integer id){
		return dao.getStocksByProduct(id);
	}
}
