package ar.com.bgr.serrano.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Contacto;

@Repository
public class ContactoDao extends AbstracDAO<Contacto>{

	@Autowired
	private SessionFactory sessionFactory;

	public ContactoDao() {
		super(Contacto.class);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
