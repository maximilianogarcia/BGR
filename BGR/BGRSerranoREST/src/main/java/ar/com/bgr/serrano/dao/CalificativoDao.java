package ar.com.bgr.serrano.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Calificativo;


@Repository
public class CalificativoDao extends AbstracDAO<Calificativo>{

	@Autowired
	private SessionFactory sessionFactory;
	
	public CalificativoDao() {
		super(Calificativo.class);
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
