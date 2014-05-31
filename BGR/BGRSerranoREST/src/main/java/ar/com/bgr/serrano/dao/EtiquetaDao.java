package ar.com.bgr.serrano.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Etiqueta;


@Repository
public class EtiquetaDao extends AbstracDAO<Etiqueta>{

	@Autowired
	private SessionFactory sessionFactory;
	
	public EtiquetaDao() {
		super(Etiqueta.class);
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
