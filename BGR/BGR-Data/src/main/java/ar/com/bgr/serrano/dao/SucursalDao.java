package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	public List<Sucursal>listByProveedor(int id){
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(getClasz());
		criteria.add(Restrictions.eq("eoi.id", id));
		return (List<Sucursal>) criteria.list();
	}
}
