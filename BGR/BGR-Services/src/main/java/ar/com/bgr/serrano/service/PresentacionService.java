package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.PaqueteDAO;
import ar.com.bgr.serrano.dao.PresentacionDAO;
import ar.com.bgr.serrano.model.Paquete;
import ar.com.bgr.serrano.model.Presentacion;
import ar.com.bgr.serrano.model.Stock;

@Service
@Transactional
public class PresentacionService {

	@Autowired
	PresentacionDAO dao;
	@Autowired
	PaqueteDAO paqueteDAO;
	
	public Presentacion save(Presentacion presentacion) {
		Presentacion pres = dao.saveOrUpdate(presentacion);
		
		for (int i =0; i< presentacion.getCantidad_paquetes(); i++){
			Paquete paquete = new Paquete();
			paquete.setPresentacion_id(presentacion.getId());
			paquete.setCodigo("un codigo");
			paquete.setEstado("DISPONIBLE");
			paqueteDAO.saveOrUpdate(paquete);
		}
		return pres;
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
	
	public List<Stock> getStocks(){
		return dao.getStocks();
	}
	
	public List<Stock> getStocksByCategory(Integer id){
		return dao.getStocksByCategory(id);		
	}
	
	public List<Object[]> getStocksByProduct(Integer id){
		return dao.getStocksByProduct(id);
	}
}
