package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Contacto;
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
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("eoi.id", id));
		return (List<Sucursal>) criteria.list();
	}

	public Sucursal addExistingContact(Contacto contacto, int sucursalID){
		Sucursal sucursal = this.getById(sucursalID);
		contacto.setEoi(sucursal.getEoi());
		sucursal.getContactos().add(contacto);
		return this.saveOrUpdate(sucursal);
	}
}
