package ar.com.bgr.serrano.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Sucursal;

@Repository
public class SucursalDao extends AbstractDAO<Sucursal>{

	@Autowired
	private SessionFactory sessionFactory;

	public SucursalDao() {
		setClasz(Sucursal.class);
	}

}
