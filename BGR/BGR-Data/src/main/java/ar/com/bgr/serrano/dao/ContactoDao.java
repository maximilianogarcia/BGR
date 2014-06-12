package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Contacto;

/**
 * 
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 31/05/2014
 */
@Repository
public class ContactoDao extends AbstractDAO<Contacto>{

	public ContactoDao() {
		setClasz(Contacto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Contacto>listBySucursal(int id){
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(getClasz());
		criteria.add(Restrictions.eq("sucursal.id", id));
		return (List<Contacto>) criteria.list();
	}
}
