package ar.com.bgr.serrano.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Sucursal;

@Repository
public class SucursalDao extends AbstracDAO<Sucursal>{

	@Autowired
	private SessionFactory sessionFactory;

	public SucursalDao() {
		super(Sucursal.class);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
